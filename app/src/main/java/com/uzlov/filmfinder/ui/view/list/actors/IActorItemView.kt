package com.uzlov.filmfinder.ui.view.list.actors

import com.uzlov.filmfinder.ui.view.list.IItemView

interface IActorItemView : IItemView {
    fun setTitle(text: String)
    fun loadPoster(url: String)
}