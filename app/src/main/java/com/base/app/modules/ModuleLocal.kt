package com.base.app.modules

import androidx.room.Room
import com.base.app.data_local.core.AppDataBase
import com.base.app.data_local.data_source.FilmLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "film_database"
        ).build()
    }

    single { get<AppDataBase>().filmDao() }

    single { FilmLocalDataSource(get()) }
}