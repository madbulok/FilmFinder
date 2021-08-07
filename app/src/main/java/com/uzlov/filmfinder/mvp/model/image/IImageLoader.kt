package com.uzlov.filmfinder.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}