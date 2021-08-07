package com.uzlov.filmfinder.mvp.model.repo

import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFilmRepository(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IFilmCache
) : IFilmRepo {

    override fun loadPopularFilms() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline){
            api.getPopularFilms()
        } else {
            api.getPopularFilms()
        }
    }.subscribeOn(Schedulers.io())

    override fun loadUpcomingFilms()  = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline){
            api.getUpcomingFilms()
        } else {
            api.getUpcomingFilms()
        }
    }.subscribeOn(Schedulers.io())

    override fun loadTopRatedFilms()  = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline){
            api.getTopFilms()
        } else {
            api.getTopFilms()
        }
    }.subscribeOn(Schedulers.io())
}