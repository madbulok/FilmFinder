package com.uzlov.filmfinder.ui.view.list.films

import com.uzlov.filmfinder.ui.view.list.IItemView

interface FilmItemView: IItemView {
    fun setTitle(text: String)
    fun loadPoster(url: String)
    fun setRating(countStar: Double)
}