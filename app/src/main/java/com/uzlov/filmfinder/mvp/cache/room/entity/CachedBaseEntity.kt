package com.uzlov.filmfinder.mvp.cache.room.entity

open class CachedBaseEntity(
    open val id: Int,
    open val title: String,
    open val picture: String,
    open val rating: Double,
    open val description: String
){
    fun getImage50(): String {
        return "https://image.tmdb.org/t/p/w500$picture"
    }

    fun getImageOriginal(): String {
        return "https://image.tmdb.org/t/p/original$picture"
    }

}