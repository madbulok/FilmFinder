package com.uzlov.filmfinder.mvp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FilmFavoriteEntityDB::class,
        CachedFilmEntity::class,
        RoomCachedImage::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "local.db"
    }

    abstract val cachedFilmDAO: CachedFilmDAO
    abstract val imageDao: ImageDao
}