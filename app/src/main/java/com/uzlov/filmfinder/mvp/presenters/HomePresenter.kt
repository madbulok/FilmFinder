package com.uzlov.filmfinder.mvp.presenters

import com.uzlov.filmfinder.mvp.model.entity.Result
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.mvp.presenters.list.IFilmsListPresenter
import com.uzlov.filmfinder.ui.view.HomeView
import com.uzlov.filmfinder.ui.view.list.FilmItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class HomePresenter : MvpPresenter<HomeView>() {

    @Inject  @field:Named("ui")
    lateinit var scheduler: Scheduler

    class FilmsListPresenter : IFilmsListPresenter {
        val films = mutableListOf<Result>()
        override var itemClickListener: ((FilmItemView) -> Unit)? = null

        override fun getCount() = films.size

        override fun bindView(view: FilmItemView) {
            val film = films[view.pos]
            film.title.let { view.setTitle(it) }
            film.getImageOriginal().let { view.loadPoster(it) }
            view.setRating(film.vote_average.toFloat())
        }

        fun clear() = films.clear()

        fun setFilms(list: List<Result>) {
            clear()
            films.addAll(list)
        }
    }

    private val popularFilmsListPresenter = FilmsListPresenter()
    private val topFilmsListPresenter = FilmsListPresenter()
    private val upcomingFilmsListPresenter = FilmsListPresenter()

    val popularListPresenter get() = popularFilmsListPresenter
    val topListPresenter get() = topFilmsListPresenter
    val upcomingListPresenter get() = upcomingFilmsListPresenter

    @Inject
    lateinit var filmsRepository: IFilmRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        startLoadingFilms()
        viewState.init()

        popularFilmsListPresenter.itemClickListener = { itemView ->
            val film = popularFilmsListPresenter.films[itemView.pos]
//            router.navigateTo(screens.user(film))
        }
    }

    private fun startLoadingFilms() {
        filmsRepository.loadPopularFilms()
            .observeOn(scheduler)
            .subscribe({ pageFilms ->
                popularFilmsListPresenter.setFilms(pageFilms.results)
                viewState.loadPopularFilms()
            }, {
                viewState.showError(it.message ?: "Unknown error")
            })
        filmsRepository.loadTopRatedFilms()
            .observeOn(scheduler)
            .subscribe({ pageFilms ->
                topFilmsListPresenter.setFilms(pageFilms.results)
                viewState.loadTopFilms()
            }, {
                viewState.showError(it.message ?: "Unknown error")
            })
        filmsRepository.loadUpcomingFilms()
            .observeOn(scheduler)
            .subscribe({ pageFilms ->
                upcomingFilmsListPresenter.setFilms(pageFilms.results)
                viewState.loadUpcomingFilms()
            }, {
                viewState.showError(it.message ?: "Unknown error")
            })
    }

    fun backPressed(): Boolean {
//        router.exit()
        return true
    }
}