package com.uzlov.filmfinder

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.verify
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedBaseEntity
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import com.uzlov.filmfinder.mvp.model.entity.Result
import com.uzlov.filmfinder.mvp.model.repo.IFilmRepo
import com.uzlov.filmfinder.mvp.presenters.FilmPresenter
import com.uzlov.filmfinder.ui.view.FilmView
import com.uzlov.filmfinder.ui.view.`FilmView$$State`
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


class FilmPresenterTest {

    private val filmMock = Film.convertFromOther(
        CachedBaseEntity(
            id = 1,
            title = "",
            picture = "",
            rating = 2.0,
            description = "",
        )
    )

    private val popularFilms = PopularFilms(
        page = 1,
        results = MutableList(10) { i -> Result(id = i) },
        total_pages = 1,
        total_results = 1,
    )

    val id = 1

    private val creditsMock = Credits(emptyList(), id)

    @Mock lateinit var view: FilmView
    @Mock lateinit var viewState: `FilmView$$State`

    private lateinit var presenter: FilmPresenter

    @Mock
    lateinit var repo: IFilmRepo

    @Mock
    lateinit var router: Router

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        presenter = FilmPresenter(id, repo, router, Schedulers.trampoline())
        presenter.attachView(view)
        presenter.setViewState(viewState)

        val call = Single.just(filmMock)
        val callFilms = Single.just(popularFilms)
        val maybeFilms: Maybe<Credits> = Maybe.just(creditsMock)

        `when`(repo.loadCachedFilmInformation(id)).thenReturn(call)
        `when`(repo.loadPopularFilms()).thenReturn(callFilms)
        `when`(repo.loadTopRatedFilms()).thenReturn(callFilms)
        `when`(repo.loadUpcomingFilms()).thenReturn(callFilms)

        `when`(repo.getCreditsMovieById(id)).thenReturn(maybeFilms)
    }

    @Test
    fun filmPresenterIsNotNull() {
        assertNotNull(presenter)
    }

    // тестируем вызов метода загрузки информации к фильму
    @Test
    fun filmPresenter_loadCachedFilmInformation_Test(){
        presenter.loadingFilm()
        verify(repo, times(1)).loadCachedFilmInformation(id)
    }

    // тестируем вызов метода загрузки актеров к фильму
    @Test
    fun filmPresenter_getCreditsMovieById_Test(){
        presenter.loadingFilm()
        verify(repo, times(1)).getCreditsMovieById(id)
    }

    // тестируем вызов метода view чтоб показать фильм
    @Test
    fun filmPresenterShowResultFilm_Test(){
        presenter.loadingFilm()
        verify(viewState).loadFilm(filmMock)
    }

    // тестируем вызов метода view если loadCachedFilmInformation вернет Error
    @Test
    fun loadCachedFilmInformationNullFilm_Test(){

        val call = Single.error<Film>(RuntimeException("Error"))
        `when`(repo.loadCachedFilmInformation(id)).thenReturn(call)

        presenter.loadingFilm()
        verify(viewState, times(1)).showError("Error")
    }

    // тестируем вызов метода view если getCreditsMovieById вернет Error
    @Test
    fun getCreditsMovieByIdNullFilm_Test(){
        val maybeFilms: Maybe<Credits> = Maybe.error<Credits>(RuntimeException("Credits error"))
        `when`(repo.getCreditsMovieById(id)).thenReturn(maybeFilms)

        presenter.loadingFilm()
        verify(viewState, times(1)).showError("Credits error")
    }
}