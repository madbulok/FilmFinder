package com.uzlov.filmfinder.mvp.cache.room

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedTopFilm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ITopFilmsCache {
    fun getTopFilms(): Single<List<CachedTopFilm>>
    fun putTopFilms(upcomingFilms: List<CachedTopFilm>) : Completable
    fun getCachedTopFilm(id: Int): Single<CachedTopFilm?>
}