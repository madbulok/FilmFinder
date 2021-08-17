package com.uzlov.filmfinder.mvp.cache.room

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedBaseEntity
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface IFilmCache : IPopularFilmsCache, ITopFilmsCache, IUpcomingFilmsCache {
}