package com.uzlov.filmfinder.mvp.model.repo

import android.util.Log
import com.uzlov.filmfinder.mvp.cache.room.IFilmCache
import com.uzlov.filmfinder.mvp.cache.room.entity.*
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import com.uzlov.filmfinder.mvp.model.entity.Result
import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.MaybeSubject
import io.reactivex.rxjava3.subjects.SingleSubject

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
                                rating = result.vote_average,
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


    override fun loadUpcomingFilms(): Single<PopularFilms> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                val cachedFilms = mutableListOf<CachedUpcomingFilm>()
                api.getUpcomingFilms().flatMap { films ->
                    films.results.forEach { result ->
                        cachedFilms.add(
                            CachedUpcomingFilm(
                                id = result.id,
                                title = result.title,
                                picture = result.poster_path,
                                rating = result.vote_average,
                                description = result.overview
                            )
                        )
                    }
                    cache.putUpcomingFilms(cachedFilms).toSingleDefault(films)
                }
            } else {
                val popularFilms = PopularFilms(0, mutableListOf(), 0, 0)
                val observable: Observable<List<CachedUpcomingFilm>> =
                    cache.getUpcomingFilms().toObservable()

                observable.flatMap { list ->
                    Observable.fromIterable(list)
                        .map { item ->
                            popularFilms.results.add(Result().convertFromCache(item))
                        }
                }.blockingSubscribe()
                return@flatMap Single.just(popularFilms)
            }
        }.subscribeOn(Schedulers.io())
    }


    override fun loadTopRatedFilms(): Single<PopularFilms> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                val cachedFilms = mutableListOf<CachedTopFilm>()
                api.getTopFilms().flatMap { films ->
                    films.results.forEach { result ->
                        cachedFilms.add(
                            CachedTopFilm(
                                id = result.id,
                                title = result.title,
                                picture = result.poster_path,
                                rating = result.vote_average,
                                description = result.overview
                            )
                        )
                    }
                    cache.putTopFilms(cachedFilms).toSingleDefault(films)
                }
            } else {
                val popularFilms = PopularFilms(0, mutableListOf(), 0, 0)
                val observable: Observable<List<CachedTopFilm>> =
                    cache.getTopFilms().toObservable()

                observable.flatMap { list ->
                    Observable.fromIterable(list)
                        .map { item ->
                            popularFilms.results.add(Result().convertFromCache(item))
                        }
                }.blockingSubscribe()
                return@flatMap Single.just(popularFilms)
            }
        }.subscribeOn(Schedulers.io())


    override fun loadCachedFilmInformation(id: Int): Single<Film?> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getFilmById(id)
            } else {
                val singleResult: SingleSubject<Film> = SingleSubject.create()

                cache.getCachedTopFilm(id).flatMap {
                    Single.just(it).map { c ->
                        if (c != null) {
                            val f = Film.convertFromOther(c)
                            singleResult.onSuccess(f)
                        }
                    }
                }.blockingSubscribe({

                }, {
                    it.printStackTrace()
                })
                cache.getCachedPopularFilm(id).flatMap {
                    Single.just(it).map { c ->
                        if (c != null) {
                            val f = Film.convertFromOther(c)
                            singleResult.onSuccess(f)
                        }
                    }
                }.blockingSubscribe({
                }, {
                    it.printStackTrace()
                })

                cache.getCachedUpcomingFilm(id).flatMap {
                    Single.just(it).map { c ->
                        if (c != null) {
                            val f = Film.convertFromOther(c)
                            singleResult.onSuccess(f)
                        }
                    }
                }.blockingSubscribe({
                }, {
                })
                return@flatMap singleResult
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getCreditsMovieById(id: Int): Maybe<Credits> =
        networkStatus.isOnlineSingle().toMaybe().flatMap { isOnline ->
            if (isOnline) {
                api.getCreditsMoviesById(id).flatMap { credits ->
                    Maybe.create<Credits> {
                        it.onSuccess(credits)
                        cache.putCacheCredential(
                            CachedCredits(
                                id = credits.id,
                                cast = credits.cast
                            )
                        )
                    }
                }
            } else {
                cache.getCacheCredential(id).flatMap { cached ->
                    Maybe.create {
                        it.onSuccess(Credits(id = cached.id, cast = cached.cast))

                    }
                }
            }
        }.subscribeOn(Schedulers.io())


}