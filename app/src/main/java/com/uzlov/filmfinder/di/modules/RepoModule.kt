package com.uzlov.filmfinder.di.modules

import android.widget.ImageView
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.model.repo.FilmCache
import com.uzlov.filmfinder.mvp.model.repo.IFilmCache
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.mvp.model.repo.RetrofitFilmRepository
import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import com.uzlov.filmfinder.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IFilmCache
    ): IFilmRepo =
        RetrofitFilmRepository(api, networkStatus, cache)


    @Singleton
    @Provides
    fun cacheFilms() : IFilmCache = FilmCache()

    @Singleton
    @Provides
    fun imageLoader(networkStatus: INetworkStatus,
                    cache: IFilmCache) : IImageLoader<ImageView> = GlideImageLoader(networkStatus, cache)

}