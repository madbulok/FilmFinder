package com.uzlov.filmfinder.mvp.presenters

import com.github.terrakok.cicerone.Router
import com.uzlov.filmfinder.mvp.navigation.IScreens
import com.uzlov.filmfinder.ui.view.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.homeScreen())
    }

    fun backClicked() {
        router.exit()
    }
}