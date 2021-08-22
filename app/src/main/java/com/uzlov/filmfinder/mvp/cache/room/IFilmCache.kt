package com.uzlov.filmfinder.mvp.cache.room

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedCredits
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface IFilmCache : IPopularFilmsCache, ITopFilmsCache, IUpcomingFilmsCache {
    fun getCacheCredential(id: Int): Maybe<CachedCredits>
    fun putCacheCredential(credits: CachedCredits): Completable
}