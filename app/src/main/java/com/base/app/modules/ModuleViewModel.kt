package com.base.app.modules

import com.base.app.presentation.film_favorites.view_model.FavoriteFilmsViewModel
import com.base.app.presentation.film_detail.view_model.FilmDetailViewModel
import com.base.app.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel {
        FilmDetailViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        FavoriteFilmsViewModel(
            get(),
            get(),
            get(),
        )
    }
}