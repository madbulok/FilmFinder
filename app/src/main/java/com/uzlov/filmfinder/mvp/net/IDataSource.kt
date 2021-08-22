package com.uzlov.filmfinder.mvp.net

import com.uzlov.filmfinder.app.Constants
import com.uzlov.filmfinder.mvp.model.entity.ActorDescription
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.entity.PopularFilms
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IDataSource {
    @GET("3/movie/popular")
    fun getPopularFilms(
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
        @Query("page") page: Long = 1
    ) : Single<PopularFilms>

    @GET("3/movie/top_rated")
    fun getTopFilms(
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
        @Query("page") page: Long = 1
    ) : Single<PopularFilms>

    @GET("3/movie/upcoming")
    fun getUpcomingFilms(
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
        @Query("page") page: Long = 1
    ) : Single<PopularFilms>


    @GET("3/movie/{id}")
    fun getFilmById(
        @Path("id") id: Int,
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
    ) : Single<Film?>

    @GET("3/movie/{movie_id}/credits")
    fun getCreditsMoviesById(
        @Path("movie_id") id: Int,
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
    ) : Maybe<Credits>

    @GET("3/search/movie")
    fun searchFilmByName(
        @Query("api_key") apikey:String = Constants.API_KEY,
        @Query("language") language:String = "ru-RU",
        @Query("query") name: String,
        @Query("page") num: Int = 1,
        @Query("include_adult") isAdult: Boolean = true
    ) : Call<PopularFilms>

    @GET("3/person/{person_id}")
    fun getActorDescriptionById(
        @Path("person_id") id: Int,
        @Query("api_key") apikey: String = Constants.API_KEY,
        @Query("language") language: String = "ru-RU"
    ): Call<ActorDescription>
}