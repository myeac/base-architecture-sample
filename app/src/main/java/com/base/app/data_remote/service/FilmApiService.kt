package com.base.app.data_remote.service

import com.base.app.core.BaseResponse
import com.base.app.data_remote.dto.BaseResponseDTO
import com.movies.app.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApiService {

    companion object {
        const val PARAM_QUERY_TITLE = "t"
        const val PARAM_QUERY_ID = "i"
        const val PARAM_QUERY_YEAR = "y"
        const val PARAM_QUERY_PLOT = "plot"
        const val PARAM_QUERY_API_KEY = "apikey"

        const val PARAM_QUER_PLOT_DEFAULT = "full"
    }

    @GET("/")
    suspend fun findFilmByTitle(
        @Query(PARAM_QUERY_TITLE) title: String,
        @Query(PARAM_QUERY_YEAR) year: String? = null,
        @Query(PARAM_QUERY_PLOT) plot: String? = PARAM_QUER_PLOT_DEFAULT,
        @Query(PARAM_QUERY_API_KEY) apiKey: String = BuildConfig.BUILD_API_KEY,
    ): BaseResponse<BaseResponseDTO>

    @GET("/")
    suspend fun findFilmById(
        @Query(PARAM_QUERY_ID) imdbId: String,
        @Query(PARAM_QUERY_PLOT) plot: String? = PARAM_QUER_PLOT_DEFAULT,
        @Query(PARAM_QUERY_API_KEY) apiKey: String = BuildConfig.BUILD_API_KEY
    ): BaseResponse<BaseResponseDTO>
}