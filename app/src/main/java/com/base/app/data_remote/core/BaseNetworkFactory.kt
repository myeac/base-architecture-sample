package com.base.app.data_remote.core

import com.base.app.data_remote.dto.BaseErrorDTO
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class BaseNetworkCallAdapter<T : Any>(
    private val responseType: Type,
    private val errorConverter: Converter<ResponseBody, BaseErrorDTO>
) : CallAdapter<T, Call<BaseResponse<T>>> {

    override fun responseType(): Type = responseType

    override fun adapt(
        call: Call<T>
    ): Call<BaseResponse<T>> = BaseNetworkCall(call, errorConverter)
}

class BaseNetworkFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != BaseResponse::class.java) return null

        val successType = getParameterUpperBound(0, callType as ParameterizedType)

        val errorConverter = retrofit.nextResponseBodyConverter<BaseErrorDTO>(
            null,
            BaseErrorDTO::class.java,
            annotations
        )
        return BaseNetworkCallAdapter<Any>(successType, errorConverter)
    }
}

class BaseNetworkCall<T : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, BaseErrorDTO>
) : Call<BaseResponse<T>> {

    override fun enqueue(
        callback: Callback<BaseResponse<T>>
    ) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    val errorBody = error?.let { errorConverter.convert(it) }
                    callback.onResponse(
                        this@BaseNetworkCall,
                        Response.success(
                            BaseResponse.Error(errorBody?.errorMessages ?: "HTTP Error $code", code)
                        )
                    )
                    return
                }

                if (body != null) {
                    val isOmdbError = try {
                        val respField = body.javaClass.getMethod("getResponse").invoke(body) as? String
                        respField == "False"
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                        false
                    }

                    if (isOmdbError) {
                        val message = try {
                            body.javaClass.getMethod("getErrorMessages").invoke(body) as? String
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                            "Not found"
                        }
                        callback.onResponse(
                            this@BaseNetworkCall,
                            Response.success(BaseResponse.SuccessNotFound(message ?: "Not Found"))
                        )
                    } else {
                        /** real success */
                        callback.onResponse(
                            this@BaseNetworkCall,
                            Response.success(BaseResponse.Success(body))
                        )
                    }
                } else {
                    callback.onResponse(
                        this@BaseNetworkCall,
                        Response.success(BaseResponse.Error("Empty Response Body", code))
                    )
                }
            }

            override fun onFailure(
                call: Call<T>,
                t: Throwable
            ) {
                callback
                    .onResponse(
                        this@BaseNetworkCall,
                        Response.success(BaseResponse.Error(t.message ?: "Network Failure")
                        )
                    )
            }
        })
    }

    override fun clone(): Call<BaseResponse<T>> = BaseNetworkCall(delegate.clone(), errorConverter)
    override fun execute(): Response<BaseResponse<T>> = throw UnsupportedOperationException("Only async calls supported")
    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun cancel() = delegate.cancel()
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}