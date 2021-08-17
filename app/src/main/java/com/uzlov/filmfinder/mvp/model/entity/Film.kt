package com.uzlov.filmfinder.mvp.model.entity

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedBaseEntity

data class Film(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun getImage50(): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

    fun getImageOriginal(): String {
        return "https://image.tmdb.org/t/p/original$poster_path"
    }

    companion object {
        fun convertFromOther(other: CachedBaseEntity): Film {
            return Film(
                adult = false,
                backdrop_path = "",
                belongs_to_collection = Any(),
                budget = 0,
                genres = emptyList(),
                homepage = "",
                id = other.id,
                imdb_id = "",
                original_language = "eng",
                original_title = other.title,
                overview = other.description,
                popularity = 0.0,
                poster_path = other.picture,
                production_companies = emptyList(),
                production_countries = emptyList(),
                release_date = "",
                revenue = 0,
                runtime = 0,
                spoken_languages = emptyList<SpokenLanguage>(),
                status = "",
                tagline = "",
                title = other.title,
                video = false,
                vote_average = other.rating,
                vote_count = 0
            )
        }
    }

}