package com.uzlov.filmfinder.di.modules

import android.content.Context
import androidx.room.Room
import com.uzlov.filmfinder.mvp.cache.RoomFilmCacheRepo
import com.uzlov.filmfinder.mvp.cache.room.IFilmCache
import com.uzlov.filmfinder.mvp.cache.room.LocalDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: Context) = Room.databaseBuilder(app, LocalDatabase::class.java, LocalDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun iFilmsCache(database: LocalDatabase): IFilmCache = RoomFilmCacheRepo(database)
}