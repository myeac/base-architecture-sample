package com.base.app.domain.repository

import com.base.app.domain.model.FilmModel

interface FilmRepository {
    suspend fun fetchFilmByTitle(title: String): FilmModel
    suspend fun fetchFilmById(imdbId: String): FilmModel
}