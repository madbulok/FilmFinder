package com.uzlov.filmfinder.mvp.cache.room

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedUpcomingFilm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUpcomingFilmsCache {
    fun getUpcomingFilms(): Single<List<CachedUpcomingFilm>>
    fun putUpcomingFilms(upcomingFilms: List<CachedUpcomingFilm>) : Completable
    fun getCachedUpcomingFilm(id: Int): Single<CachedUpcomingFilm?>
}