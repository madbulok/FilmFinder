package com.uzlov.filmfinder.mvp.model.repo

interface IFilmCache {
    fun saveImage(url: String, bytes: ByteArray): Any
}