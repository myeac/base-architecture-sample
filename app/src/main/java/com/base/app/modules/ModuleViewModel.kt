package com.base.app.modules

import com.base.app.presentation.FavoriteFilmsViewModel
import com.base.app.presentation.FilmViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FilmViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
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