package com.uzlov.filmfinder.mvp.cache.room.dao

import androidx.room.*
import com.uzlov.filmfinder.mvp.cache.room.entity.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface CachedFilmDAO {

    // cached UPCOMING films
    @Query("SELECT * FROM CachedUpcomingFilm")
    fun getCachedUpcomingFilms() : Single<List<CachedUpcomingFilm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUpcomingFilms(vararg users: CachedUpcomingFilm)  : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUpcomingFilms(users: CachedUpcomingFilm) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUpcomingFilms(users: List<CachedUpcomingFilm>) : Completable

    @Query("SELECT * FROM CachedUpcomingFilm WHERE id=:id ")
    fun getCachedUpcomingFilmById(id: Int) : Single<CachedUpcomingFilm?>


    // cached TOP films
    @Query("SELECT * FROM CachedTopFilm")
    fun getCachedTopFilms() : Single<List<CachedTopFilm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopFilms(vararg users: CachedTopFilm)  : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopFilms(users: CachedTopFilm)  : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopFilms(users: List<CachedTopFilm>) : Completable

    @Query("SELECT * FROM CachedTopFilm WHERE id=:id")
    fun getCachedTopFilmById(id: Int) : Single<CachedTopFilm?>


    // cached POPULAR films
    @Query("SELECT * FROM CachedPopularFilm")
    fun getCachedPopularFilms() : Single<List<CachedPopularFilm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularFilms(vararg users: CachedPopularFilm)  : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularFilms(users: CachedPopularFilm)  : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularFilms(users: List<CachedPopularFilm>) : Completable

    @Query("SELECT * FROM CachedPopularFilm WHERE id=:id")
    fun getCachedPopularFilmById(id: Int) : Single<CachedPopularFilm?>


    // other method's
    @Query("SELECT * FROM FilmFavoriteEntity")
    fun getFavoriteFilms() : Single<List<FilmFavoriteEntity>>

    @Query("SELECT COUNT(id) FROM FilmFavoriteEntity WHERE id=:id")
    fun checkFilmIsFavorite(id: Int) : Single<Int>

    @Query("DELETE FROM FilmFavoriteEntity WHERE id=:id")
    fun removeFilmFromFavorite(id: Long)

    @Query("DELETE FROM FilmFavoriteEntity WHERE id=:id")
    fun removeFilmFromFavorite(vararg id: Long)

    @Query("DELETE FROM FilmFavoriteEntity WHERE id=:id")
    fun removeFilmFromFavorite(id: List<Long>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(vararg users: FilmFavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(users: FilmFavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoFavoriteAll(users: List<FilmFavoriteEntity>)


    //credits
    @Query("SELECT * FROM CachedCredits WHERE id=:id")
    fun getCreditsByFilmID(id: Int) : Maybe<CachedCredits>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredits(vararg credits: CachedCredits) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredits(credits: CachedCredits) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredits(credits: List<CachedCredits>) : Completable


    @Delete
    fun delete(user: FilmFavoriteEntity)

    @Delete
    fun delete(vararg user: FilmFavoriteEntity)

    @Delete
    fun delete(user: List<FilmFavoriteEntity>)
}