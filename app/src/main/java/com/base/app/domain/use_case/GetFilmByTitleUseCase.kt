package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.model.FilmModel
import com.base.app.domain.repository.FilmRepository

class GetFilmByTitleUseCase(
    private val repository: FilmRepository
) : BaseUseCase<String, FilmModel>() {

    override suspend fun execute(
        params: String
    ): FilmModel {
        return repository.fetchFilmByTitle(params)
    }
}