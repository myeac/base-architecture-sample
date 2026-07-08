package com.base.app.modules

import com.base.app.domain.use_case.GetFilmByIdUseCase
import com.base.app.domain.use_case.GetFilmByTitleUseCase
import com.base.app.domain.use_case.GetListFavoriteFilmsUseCase
import com.base.app.domain.use_case.RemoveFavoriteFilmUseCase
import com.base.app.domain.use_case.SaveFavoriteFilmUseCase
import com.base.app.domain.use_case.SearchFilmInFavoritesByName
import com.base.app.domain.use_case.IsFilmFavorite
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetFilmByTitleUseCase(get()) }
    factory { GetFilmByIdUseCase(get()) }
    factory { SaveFavoriteFilmUseCase(get()) }
    factory { RemoveFavoriteFilmUseCase(get()) }
    factory { GetListFavoriteFilmsUseCase(get()) }
    factory { SearchFilmInFavoritesByName(get()) }
    factory { IsFilmFavorite(get()) }
}