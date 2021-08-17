package com.uzlov.filmfinder.mvp.cache.room.entity

open class CachedBaseEntity(
    open val id: Int,
    open val title: String,
    open val picture: String,
    open val rating: Double,
    open val description: String
){
    val image50: String get() = "https://image.tmdb.org/t/p/w500$picture"
    val imageOriginal: String get() = "https://image.tmdb.org/t/p/original$picture"

}