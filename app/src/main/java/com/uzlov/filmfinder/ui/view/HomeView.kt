package com.uzlov.filmfinder.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView : MvpView {

    fun init()
    fun loadPopularFilms()
    fun loadTopFilms()
    fun loadUpcomingFilms()
    fun showError(message: String)
}