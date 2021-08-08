package com.uzlov.filmfinder.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.uzlov.filmfinder.mvp.navigation.IScreens
import com.uzlov.filmfinder.ui.fragments.FilmFragment
import com.uzlov.filmfinder.ui.fragments.HomeFragment

class AndroidScreens : IScreens {
    override fun homeScreen(): Screen =  FragmentScreen { HomeFragment() }
    override fun openFilm(film: Int): Screen = FragmentScreen{ FilmFragment.newInstance(film) }

}