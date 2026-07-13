package com.base.app.modules

import com.base.app.data_remote.core.BaseNetworkFactory
import com.base.app.data_remote.repository.FilmRepositoryImpl
import com.base.app.data_remote.service.FilmApiService
import com.base.app.domain.repository.FilmRepository
import com.movies.app.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val baseUrl = BuildConfig.BUILD_BASE_URL
private val json = Json { ignoreUnknownKeys = true }

val remoteModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(BaseNetworkFactory())
            .build()
    }

    /** modules */
    single { get<Retrofit>().create(FilmApiService::class.java) }

    /** repository */
    single<FilmRepository> { FilmRepositoryImpl(get()) }
}