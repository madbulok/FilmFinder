package com.uzlov.filmfinder.mvp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uzlov.moviefind.database.FilmDAO
import com.uzlov.moviefind.database.FilmEntityDB

@Database(
    entities = [FilmEntityDB::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "local.db"
    }
    abstract fun filmsDao() : FilmDAO
}