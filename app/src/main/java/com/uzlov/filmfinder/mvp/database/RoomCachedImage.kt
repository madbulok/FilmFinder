package com.uzlov.filmfinder.mvp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class RoomCachedImage(
    @PrimaryKey val url: String,
    val localPath: String
)