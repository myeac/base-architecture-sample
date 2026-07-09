package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource
import com.base.app.domain.model.FilmModel
import kotlinx.coroutines.flow.Flow

class SearchFavoriteByIdUseCase(
    private val dataSource: FilmDataSource
) : BaseUseCase<String, Flow<FilmModel>>() {

    override suspend fun execute(
        params: String
    ): Flow<FilmModel> {
        return dataSource.getFavoriteById(params)
    }
}