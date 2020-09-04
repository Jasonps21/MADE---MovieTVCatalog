package com.jason.movietvcatalog.core.data.source.remote

import com.jason.movietvcatalog.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") api_key: String): ListMovieResponse

    @GET("search/movie")
    suspend fun getSearchMovies(@Query("api_key") api_key: String, @Query("query") query: String): ListMovieResponse

    @GET("tv/popular")
    suspend fun getTvshows(@Query("api_key") api_key: String): ListTvshowResponse

    @GET("search/tv")
    suspend fun getSearchTvs(@Query("api_key") api_key: String, @Query("query") query: String): ListTvshowResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int, @Query("api_key") api_key: String): MovieResponse

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id: Int, @Query("api_key") api_key: String): CreditResponse

    @GET("tv/{id}")
    suspend fun getTvDetail(@Path("id") id: Int, @Query("api_key") api_key: String): TvResponse

    @GET("person/popular")
    suspend fun getPeoples(@Query("api_key") api_key: String): ListPeopleResponse
}