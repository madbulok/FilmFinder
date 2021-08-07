package com.uzlov.filmfinder.di.modules.components

import com.uzlov.filmfinder.di.modules.*
import com.uzlov.filmfinder.mvp.presenters.HomePresenter
import com.uzlov.filmfinder.mvp.presenters.MainPresenter
import com.uzlov.filmfinder.ui.activities.HostActivity
import com.uzlov.filmfinder.ui.fragments.HomeFragment
import com.uzlov.filmfinder.ui.image.GlideImageLoader
import dagger.Component
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.room.RoomImageCache
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepositoryPresenter
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserPresenter
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UsersPresenter
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity.MainActivity
//import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.UsersRVAdapter
//import com.uzlov.filmfinder.ui.image.GlideImageLoader
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        SchedulersModule::class
    ]
)

interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainPresenter: HostActivity)
    fun inject(homePresenter: HomePresenter)
    fun inject(homeFragment: HomeFragment)
}
