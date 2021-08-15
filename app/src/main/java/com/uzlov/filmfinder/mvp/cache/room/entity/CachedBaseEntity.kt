package com.uzlov.filmfinder.mvp.cache.room.entity

open class CachedBaseEntity(
    open val id: Int,
    open val title: String,
    open val picture: String,
    open val rating: Float,
    open val description: String
)