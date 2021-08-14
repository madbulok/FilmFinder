package com.uzlov.filmfinder.mvp.model.repo

import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFilmRepository(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    val cache: IPictureCache
) : IFilmRepo {

    override fun loadPopularFilms(): Single<PopularFilms> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getPopularFilms()
            } else {
                api.getPopularFilms()
            }
        }.subscribeOn(Schedulers.io())

    override fun loadUpcomingFilms(): Single<PopularFilms> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUpcomingFilms()
            } else {
                api.getUpcomingFilms()
            }
        }.subscribeOn(Schedulers.io())

    override fun loadTopRatedFilms(): Single<PopularFilms> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getTopFilms()
            } else {
                api.getTopFilms()
            }
        }.subscribeOn(Schedulers.io())

    override fun loadFilmInformation(id: Int): Single<Film> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getFilmById(id)
            } else {
                api.getFilmById(id)
            }
        }

    override fun getCreditsMovieById(id: Int): Single<Credits> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getCreditsMoviesById(id)
            } else {
                api.getCreditsMoviesById(id)
            }
        }
}