package com.uzlov.filmfinder.mvp.model.repo

import com.uzlov.filmfinder.mvp.cache.room.entity.CachedPopularFilm
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedTopFilm
import com.uzlov.filmfinder.mvp.cache.room.entity.CachedUpcomingFilm
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface IFilmRepo {
    fun loadPopularFilms(): Single<PopularFilms>
    fun loadUpcomingFilms(): Single<PopularFilms>
    fun loadTopRatedFilms(): Single<PopularFilms>
    fun loadCachedFilmInformation(id: Int): Single<Film?>
    fun getCreditsMovieById(id: Int): Single<Credits>
//    fun getCachedUpcomingFilm(id: Int): Single<CachedUpcomingFilm?>
//    fun getCachedTopFilm(id: Int): Single<CachedTopFilm?>
//    fun getCachedPopularFilm(id: Int): Single<CachedPopularFilm?>
}