package com.uzlov.filmfinder.mvp.presenters

import com.github.terrakok.cicerone.Router
import com.uzlov.filmfinder.mvp.model.entity.Cast
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.mvp.presenters.list.IActorListPresenter
import com.uzlov.filmfinder.ui.view.FilmView
import com.uzlov.filmfinder.ui.view.list.actors.IActorItemView
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

    inner class ActorsListPresenter : IActorListPresenter {
        val actors = mutableListOf<Cast>()
        override var itemClickListener: ((IActorItemView) -> Unit)? = null
        override fun bindView(view: IActorItemView) {
            with(actors[view.pos]){
                original_name.toLinedText().let { view.setTitle(it) }
                getFullImagePath().let { view.loadPoster(it) }
            }
        }

        override fun getCount() = actors.size

        fun clear() = actors.clear()

        fun setActors(list: List<Cast>) {
            clear()
            actors.addAll(list)
        }
    }

    private val actorsListPresenter by lazy {
        ActorsListPresenter()
    }

    val actorsPresenter get() = actorsListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadingFilm()
    }

    private fun loadingFilm() {
        repo.loadCachedFilmInformation(film)
            .observeOn(uiScheduler)
            .subscribe({
                if (it != null) viewState.loadFilm(it)
            }, {
                viewState.showError(it.message ?: "Unknown error!")
            })

        repo.getCreditsMovieById(film)
            .observeOn(uiScheduler)
            .subscribe({credits->
                actorsListPresenter.setActors(credits.cast)
                viewState.loadActors(credits)
            }, {
                viewState.showError(it.message ?: "Unknown error!")
            })
    }

    fun backButton(): Boolean {
        router.exit()
        return true
    }

    fun String.toLinedText() = replace(" ", System.getProperty("line.separator") ?: "\n")
}
