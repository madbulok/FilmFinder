package com.uzlov.filmfinder.mvp.model.entity

import androidx.recyclerview.widget.DiffUtil

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
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
        val callback: DiffUtil.ItemCallback<Result> = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}