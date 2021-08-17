package com.uzlov.filmfinder.mvp.cache.room

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedPopularFilm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPopularFilmsCache {
    fun getPopularFilms(): Single<List<CachedPopularFilm>>
    fun putPopularFilms(upcomingFilms: List<CachedPopularFilm>) : Completable
    fun getCachedPopularFilm(id: Int): Single<CachedPopularFilm?>
}