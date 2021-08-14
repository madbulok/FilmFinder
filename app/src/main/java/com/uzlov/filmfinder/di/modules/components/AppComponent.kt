package com.uzlov.filmfinder.di.modules.components

import com.uzlov.filmfinder.di.modules.*
import com.uzlov.filmfinder.mvp.presenters.FilmPresenter
import com.uzlov.filmfinder.mvp.presenters.HomePresenter
import com.uzlov.filmfinder.mvp.presenters.MainPresenter
import com.uzlov.filmfinder.ui.activities.HostActivity
import com.uzlov.filmfinder.ui.fragments.FilmFragment
import com.uzlov.filmfinder.ui.fragments.HomeFragment
import dagger.Component

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
    fun inject(filmFragment: FilmFragment)
    fun inject(filmPresenter: FilmPresenter)
    fun inject(companion: FilmFragment.Companion)
}
