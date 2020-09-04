package com.jason.movietvcatalog.core.domain.usecase

import com.jason.movietvcatalog.core.data.source.Resource
import com.jason.movietvcatalog.core.domain.model.Actor
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(query: String): Flow<Resource<List<Movie>>>
    fun getAllTvShows(query: String): Flow<Resource<List<Movie>>>
    fun getMoviesFavorite(): Flow<List<Movie>>
    fun getTvShowsFavorite(): Flow<List<Movie>>
    fun getMovieDetail(movieId: Int): Flow<Resource<Movie>>
    fun getTvShowDetail(tvShowId: Int): Flow<Resource<Movie>>
    fun getMovieActors(movieId: Int): Flow<Resource<List<Actor>>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getPeoples(): Flow<Resource<List<People>>>
}