package com.base.app.data_remote.repository

import com.base.app.data_remote.core.BaseApiHandler
import com.base.app.data_remote.dto.toDomain
import com.base.app.data_remote.service.FilmApiService
import com.base.app.domain.model.FilmModel
import com.base.app.domain.repository.FilmRepository

class FilmRepositoryImpl(
    private val apiService: FilmApiService
) : FilmRepository, BaseApiHandler() {

    override suspend fun fetchFilmByTitle(
        title: String
    ): FilmModel {
        return safeApiRequest(
            apiCall = { apiService.findFilmByTitle(title) },
            mapper = { it.toDomain() }
        )
    }

    override suspend fun fetchFilmById(
        imdbId: String
    ): FilmModel {
        return safeApiRequest(
            apiCall = { apiService.findFilmById(imdbId) },
            mapper = { it.toDomain() }
        )
    }
}