package com.uzlov.filmfinder.mvp.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uzlov.filmfinder.mvp.cache.room.dao.CachedFilmDAO
import com.uzlov.filmfinder.mvp.cache.room.dao.ImageDao
import com.uzlov.filmfinder.mvp.cache.room.entity.*

@Database(
    entities = [FilmFavoriteEntity::class,
        CachedUpcomingFilm::class,
        CachedPopularFilm::class,
        CachedTopFilm::class,
        RoomCachedImage::class,
               CachedCredits::class],
    version = 3
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "local.db"
    }

    abstract val filmDAO: CachedFilmDAO
    abstract val imageDao: ImageDao
}