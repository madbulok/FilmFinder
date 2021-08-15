package com.uzlov.filmfinder.mvp.model.repo

import com.uzlov.filmfinder.mvp.cache.room.IFilmCache
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedPopularFilm
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import com.uzlov.filmfinder.mvp.model.entity.Result
import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFilmRepository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    val cache: IFilmCache
) : IFilmRepo {

    override fun loadPopularFilms(): Single<PopularFilms> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getPopularFilms().flatMap { popularFilms ->
                    val cachedFilms = mutableListOf<CachedPopularFilm>()
                    popularFilms.results.forEach { result ->
                        cachedFilms.add(
                            CachedPopularFilm(
                                id = result.id,
                                title = result.title,
                                picture = result.poster_path,
                                rating = result.vote_average.toFloat(),
                                description = result.overview
                            )
                        )
                    }
                    cache.putPopularFilms(cachedFilms).toSingleDefault(popularFilms)
                }
            } else {
                val popularFilms = PopularFilms(0, mutableListOf(), 0, 0)
                val observable: Observable<List<CachedPopularFilm>> =
                    cache.getPopularFilms().toObservable()

                observable.flatMap { list ->
                    Observable.fromIterable(list)
                        .map { item ->
                            popularFilms.results.add(Result().convertFromCache(item))
                        }
                }.blockingSubscribe()
                return@flatMap Single.just(popularFilms)
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