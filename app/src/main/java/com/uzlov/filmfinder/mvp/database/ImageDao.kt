package com.uzlov.filmfinder.mvp.database

import androidx.room.*

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomCachedImage>)

    @Update
    fun update(image: RoomCachedImage)

    @Update
    fun update(vararg images: RoomCachedImage)

    @Update
    fun update(images: List<RoomCachedImage>)

    @Delete
    fun delete(image: RoomCachedImage)

    @Delete
    fun delete(vararg images: RoomCachedImage)

    @Delete
    fun delete(images: List<RoomCachedImage>)

    @Query("DELETE FROM RoomCachedImage")
    fun clear()

    @Query("SELECT * FROM RoomCachedImage")
    fun getAll(): List<RoomCachedImage>

    @Query("SELECT * FROM RoomCachedImage WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomCachedImage?
}