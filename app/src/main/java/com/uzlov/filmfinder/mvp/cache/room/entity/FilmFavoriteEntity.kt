package com.uzlov.filmfinder.mvp.cache.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmFavoriteEntity(
    @PrimaryKey val id : Long,
    val title : String,
    val picture : String,
    val rating : Float,
    val description : String
)