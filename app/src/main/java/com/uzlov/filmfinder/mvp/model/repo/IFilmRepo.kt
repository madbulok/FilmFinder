package com.uzlov.filmfinder.mvp.model.repo

import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import io.reactivex.rxjava3.core.Single

interface IFilmRepo {
    fun loadPopularFilms(): Single<PopularFilms>
    fun loadUpcomingFilms(): Single<PopularFilms>
    fun loadTopRatedFilms(): Single<PopularFilms>
}