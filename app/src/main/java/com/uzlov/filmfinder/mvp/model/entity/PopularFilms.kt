package com.uzlov.filmfinder.mvp.model.entity

data class PopularFilms(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)