package com.uzlov.filmfinder.mvp.model.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Credits(
    val cast: List<Cast>,
    val id: Int
){

}

