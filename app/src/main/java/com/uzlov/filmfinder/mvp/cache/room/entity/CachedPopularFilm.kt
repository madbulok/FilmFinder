package com.uzlov.filmfinder.mvp.cache.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedPopularFilm(
    @PrimaryKey override val id: Int,
    override val title: String,
    override val picture: String,
    override val rating: Float,
    override val description: String
) : CachedBaseEntity(id, title, picture, rating, description) {
    fun getImage50(): String {
        return "https://image.tmdb.org/t/p/w500$picture"
    }

    fun getImageOriginal(): String {
        return "https://image.tmdb.org/t/p/original$picture"
    }

}
