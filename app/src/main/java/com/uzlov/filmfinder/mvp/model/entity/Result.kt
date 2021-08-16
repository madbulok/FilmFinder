package com.uzlov.filmfinder.mvp.model.entity

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedBaseEntity

data class Result(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var genre_ids: List<Int> = emptyList(),
    var id: Int = 0,
    var original_language: String = "eng",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var release_date: String = "",
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0
) {
    val image50: String get() = "https://image.tmdb.org/t/p/w500$poster_path"
    val imageOriginal: String get() = "https://image.tmdb.org/t/p/original$poster_path"

    fun convertFromCache(film: CachedBaseEntity): Result {
        id = film.id
        title = film.title
        vote_average = film.rating
        poster_path = film.picture
        overview = film.description
        return this
    }
}