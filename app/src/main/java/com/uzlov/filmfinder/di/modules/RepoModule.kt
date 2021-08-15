package com.uzlov.filmfinder.di.modules

import android.widget.ImageView
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.mvp.cache.room.IFilmCache
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.mvp.model.repo.IPictureCache
import com.uzlov.filmfinder.mvp.model.repo.ImageCacheImpl
import com.uzlov.filmfinder.mvp.model.repo.RetrofitFilmRepository
import com.uzlov.filmfinder.mvp.net.IDataSource
import com.uzlov.filmfinder.mvp.net.INetworkStatus
import com.uzlov.filmfinder.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
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


    @Provides
    fun defaultPath() : File = App.instance.cacheDir

    @Singleton
    @Provides
    fun iPictureCache(dir: File = defaultPath()) : IPictureCache = ImageCacheImpl(dir)

    @Singleton
    @Provides
    fun imageLoader(networkStatus: INetworkStatus,
                    cache: IPictureCache) : IImageLoader<ImageView> = GlideImageLoader(networkStatus, cache)

}