package com.uzlov.filmfinder.mvp.model.repo

import android.util.Log
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.mvp.cache.room.LocalDatabase
import com.uzlov.filmfinder.mvp.cache.room.entity.RoomCachedImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.MessageDigest
import javax.inject.Inject

class ImageCacheImpl(private val dir: File)  : IPictureCache {
    @Inject
    lateinit var db: LocalDatabase

    init {
        App.instance.appComponent.inject(this)
    }

    private fun String.md5() = hash("MD5")
    private fun String.hash(algorithm: String) = MessageDigest.getInstance(algorithm).digest(toByteArray()).fold("", { _, it -> "%02x".format(it) })

    override fun getBytes(url: String): Maybe<ByteArray?> = Maybe.fromCallable {
        db.imageDao.findByUrl(url)?.let {
            File(it.localPath).inputStream().readBytes()
        }
    }.subscribeOn(Schedulers.io())

    override fun saveImage(url: String, bytes: ByteArray): Completable = Completable.create { emitter ->
        if (!dir.exists() && !dir.mkdir()) {
            emitter.onError(IOException("Failed to create cache dir"))
            return@create
        }

        val fileFormat = if (url.contains(".jpg")) ".jpg" else ".png"
        val imageFile = File(dir, url.md5() + fileFormat)
        try {
            FileOutputStream(imageFile).use {
                it.write(bytes)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
        db.imageDao.insert(RoomCachedImage(url, imageFile.path))
        Log.e("URL SAVE", url)
        emitter.onComplete()
    }.subscribeOn(Schedulers.io())

    override fun contains(url: String): Single<Boolean> = Single.fromCallable { db.imageDao.findByUrl(url) != null }.subscribeOn(
        Schedulers.io())

    override fun clear(): Completable = Completable.fromAction {
        db.imageDao.clear()
        dir.deleteRecursively()
    }.subscribeOn(Schedulers.io())
}