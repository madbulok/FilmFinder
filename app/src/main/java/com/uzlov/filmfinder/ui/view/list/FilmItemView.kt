package com.uzlov.filmfinder.ui.view.list

interface FilmItemView: IItemView {
    fun setTitle(text: String)
    fun loadPoster(url: String)
    fun setRating(countStar: Float)
}