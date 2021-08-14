package com.uzlov.filmfinder.mvp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmFavoriteEntityDB(
    @PrimaryKey val id : Long,
    val title : String,
    val picture : String,
    val rating : Float,
    val description : String
)