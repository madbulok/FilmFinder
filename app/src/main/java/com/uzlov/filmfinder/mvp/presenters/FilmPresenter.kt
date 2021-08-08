package com.uzlov.filmfinder.mvp.presenters

import com.github.terrakok.cicerone.Router
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.ui.view.FilmView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class FilmPresenter(val film: Int) : MvpPresenter<FilmView>() {

    @Inject
    lateinit var repo: IFilmRepo

    @Inject
    lateinit var router: Router

    @Inject
    @field:Named("ui")
    lateinit var uiScheduler: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadingFilm()
    }

    private fun loadingFilm() {
        repo.loadFilmInformation(film)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.loadFilm(it)
            }, {
                viewState.showError(it.message ?: "Unknown error!")
            })
    }

    fun backButton(): Boolean {
        router.exit()
        return true
    }
}