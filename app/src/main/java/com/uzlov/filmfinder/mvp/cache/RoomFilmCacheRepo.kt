package com.uzlov.filmfinder.mvp.cache

import com.uzlov.filmfinder.mvp.cache.room.IFilmCache
import com.uzlov.filmfinder.mvp.cache.room.LocalDatabase
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedPopularFilm
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedTopFilm
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedUpcomingFilm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomFilmCacheRepo(
    private val db: LocalDatabase
) : IFilmCache {

    // for upcoming films
    override fun getUpcomingFilms(): Single<List<CachedUpcomingFilm>> {
        return db.filmDAO.getCachedUpcomingFilms().subscribeOn(Schedulers.io()) ?: throw RuntimeException(
            "Неизвестная ошибка"
        )
    }

    override fun putUpcomingFilms(upcomingFilms: List<CachedUpcomingFilm>): Completable {
        return db.filmDAO.saveUpcomingFilms(upcomingFilms).subscribeOn(Schedulers.io())
    }

    override fun getCachedUpcomingFilm(id: Int): Single<CachedUpcomingFilm?> {
        return db.filmDAO.getCachedUpcomingFilmById(id)
    }

    // for top films
    override fun getTopFilms(): Single<List<CachedTopFilm>> {
        return db.filmDAO.getCachedTopFilms().subscribeOn(Schedulers.io())
            ?: throw RuntimeException("Неизвестная ошибка")
    }

    override fun putTopFilms(upcomingFilms: List<CachedTopFilm>): Completable {
        return db.filmDAO.saveTopFilms(upcomingFilms).subscribeOn(Schedulers.io())
    }

    override fun getCachedTopFilm(id: Int): Single<CachedTopFilm?> {
        return db.filmDAO.getCachedTopFilmById(id)
    }


    // for popular films
    override fun getPopularFilms(): Single<List<CachedPopularFilm>> {
        return db.filmDAO.getCachedPopularFilms().subscribeOn(Schedulers.io())
            ?: throw RuntimeException("Неизвестная ошибка")
    }

    override fun putPopularFilms(upcomingFilms: List<CachedPopularFilm>): Completable {
        return db.filmDAO.savePopularFilms(upcomingFilms).subscribeOn(Schedulers.io())
    }

    override fun getCachedPopularFilm(id: Int): Single<CachedPopularFilm?> {
        return db.filmDAO.getCachedPopularFilmById(id)
    }
}