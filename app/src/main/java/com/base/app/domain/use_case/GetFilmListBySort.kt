package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource
import com.base.app.domain.model.FilmModel
import com.base.app.domain.model.FilmSortStateModel
import kotlinx.coroutines.flow.Flow

class GetFilmListBySort(
    private val dataSource: FilmDataSource
) : BaseUseCase<FilmSortStateModel, Flow<List<FilmModel>>>() {

    override suspend fun execute(
        params: FilmSortStateModel
    ): Flow<List<FilmModel>> {
        return when (params) {
            FilmSortStateModel.ASC -> dataSource.getFavoritesSortedAsc()
            FilmSortStateModel.DESC -> dataSource.getFavoritesSortedDesc()
        }
    }
}