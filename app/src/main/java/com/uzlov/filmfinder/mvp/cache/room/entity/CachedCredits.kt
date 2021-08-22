package com.uzlov.filmfinder.mvp.cache.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uzlov.filmfinder.mvp.model.entity.Cast

@Entity
@TypeConverters(MainConverter::class)
data class CachedCredits(
    @PrimaryKey val id: Int,
    val cast: List<Cast>
)

class MainConverter{
    @TypeConverter
    fun toCast(listCast: List<Cast>) = Gson().toJson(listCast)


    @TypeConverter
    fun fromCast(listCast: String):List<Cast> {
        val type = object : TypeToken<List<Cast>>() {}.type
        return Gson().fromJson(listCast, type)
    }
}


