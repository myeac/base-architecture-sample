package com.base.app.data_local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.base.app.data_local.dao.FilmDao
import com.base.app.data_local.tools.FilmRatingConverter
import com.base.app.data_local.entity.FilmEntity

@Database(
    entities = [FilmEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FilmRatingConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}