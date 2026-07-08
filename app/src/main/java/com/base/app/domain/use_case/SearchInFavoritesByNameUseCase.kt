package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource
import com.base.app.domain.model.FilmModel
import kotlinx.coroutines.flow.Flow

class SearchInFavoritesByNameUseCase(
    private val dataSource: FilmDataSource
) : BaseUseCase<String, Flow<List<FilmModel>>>() {

    override suspend fun execute(
        params: String
    ): Flow<List<FilmModel>> {
        return dataSource.searchFavorites(params)
    }
}