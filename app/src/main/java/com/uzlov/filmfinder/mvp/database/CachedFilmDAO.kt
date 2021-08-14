package com.uzlov.filmfinder.mvp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CachedFilmDAO {
    @Query("SELECT * FROM CachedFilmEntity")
    fun getAllSavedFilms() : LiveData<List<CachedFilmEntity>>

    @Query("SELECT * FROM CachedFilmEntity WHERE id=:id")
    fun getSavedFilmById(id : Long) : LiveData<FilmFavoriteEntityDB>

    @Query("SELECT * FROM FilmFavoriteEntityDB")
    fun getSavedFavoriteFilm() : LiveData<List<FilmFavoriteEntityDB>>

    @Query("SELECT COUNT(id) FROM FilmFavoriteEntityDB WHERE id=:id")
    fun checkFilmIsFavorite(id: Int) : LiveData<Int>

    @Query("DELETE FROM FilmFavoriteEntityDB WHERE id=:id")
    fun removeFilmFromFavorite(id: Long)

    @Query("DELETE FROM FilmFavoriteEntityDB WHERE id=:id")
    fun removeFilmFromFavorite(vararg id: Long)

    @Query("DELETE FROM FilmFavoriteEntityDB WHERE id=:id")
    fun removeFilmFromFavorite(id: List<Long>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(vararg users: FilmFavoriteEntityDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(users: FilmFavoriteEntityDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(users: List<FilmFavoriteEntityDB>)

    @Delete
    fun delete(user: FilmFavoriteEntityDB)

    @Delete
    fun delete(vararg user: FilmFavoriteEntityDB)

    @Delete
    fun delete(user: List<FilmFavoriteEntityDB>)
}