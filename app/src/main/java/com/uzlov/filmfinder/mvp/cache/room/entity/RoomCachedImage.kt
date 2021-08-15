package com.uzlov.filmfinder.mvp.cache.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class RoomCachedImage(
    @PrimaryKey val url: String,
    val localPath: String
)