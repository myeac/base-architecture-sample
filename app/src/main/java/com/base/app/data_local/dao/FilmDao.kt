package com.base.app.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.base.app.data_local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    companion object {
        const val QUERY_DELETE_FILM_WITH_ID = "DELETE FROM films WHERE imdbID = :imdbId"
        const val QUERY_SELECT_ALL_FILMS = "SELECT * FROM films"
        const val QUERY_SELECT_BY_ID = "SELECT * FROM films WHERE imdbID = :imdbId"
        const val QUERY_SELECT_BY_NAME = "SELECT * FROM films WHERE title LIKE '%' || :name || '%'"
        const val QUERY_SELECT_BY_ORDER_ASC = "SELECT * FROM films ORDER BY title ASC"
        const val QUERY_SELECT_BY_ORDER_DESC = "SELECT * FROM films ORDER BY title DESC"
        const val QUERY_FLAG_IF_EXISTS = "SELECT EXISTS(SELECT 1 FROM films WHERE imdbID = :imdbID)"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Query(QUERY_DELETE_FILM_WITH_ID)
    suspend fun deleteFilmById(imdbId: String)

    @Query(QUERY_SELECT_ALL_FILMS)
    fun getAllFavoriteFilms(): Flow<List<FilmEntity>>

    @Query(QUERY_SELECT_BY_ID)
    fun searchFilmById(imdbId: String): Flow<FilmEntity?>

    @Query(QUERY_SELECT_BY_NAME)
    fun searchFilmsByName(name: String): Flow<List<FilmEntity>>

    @Query(QUERY_SELECT_BY_ORDER_ASC)
    fun getFilmsOrderedByNameAsc(): Flow<List<FilmEntity>>

    @Query(QUERY_SELECT_BY_ORDER_DESC)
    fun getFilmsOrderedByNameDesc(): Flow<List<FilmEntity>>

    @Query(QUERY_FLAG_IF_EXISTS)
    suspend fun isFilmFavorite(imdbID: String): Boolean
}