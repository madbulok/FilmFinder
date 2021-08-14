package com.uzlov.filmfinder.ui.view

import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilmView : MvpView{
    fun init()
    fun loadFilm(film: Film)
    fun loadActors(film: Credits)
    fun showError(message: String)
}