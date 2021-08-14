package com.uzlov.filmfinder.di.modules

import android.content.Context
import androidx.room.Room
import com.uzlov.filmfinder.mvp.database.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: Context) = Room.databaseBuilder(app, LocalDatabase::class.java, LocalDatabase.DB_NAME)
        .build()
}