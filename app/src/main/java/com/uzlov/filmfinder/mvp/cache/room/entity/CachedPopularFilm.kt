package com.uzlov.filmfinder.mvp.cache.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedPopularFilm(
    @PrimaryKey override val id: Int,
    override val title: String,
    override val picture: String,
    override val rating: Double,
    override val description: String
) : CachedBaseEntity(id, title, picture, rating, description)
