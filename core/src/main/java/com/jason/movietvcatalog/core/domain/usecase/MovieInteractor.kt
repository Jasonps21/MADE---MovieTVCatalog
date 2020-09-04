package com.jason.movietvcatalog.core.domain.usecase

import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.core.domain.repository.IMovieRepository

class MovieInteractor (private val movieRepository: IMovieRepository): MovieUseCase{
    override fun getAllMovies(query: String) = movieRepository.getAllMovies(query)

    override fun getAllTvShows(query: String) = movieRepository.getAllTvShows(query)

    override fun getMoviesFavorite() = movieRepository.getMoviesFavorite()

    override fun getTvShowsFavorite() = movieRepository.getTvShowsFavorite()

    override fun getMovieDetail(movieId: Int) = movieRepository.getMovieDetail(movieId)

    override fun getTvShowDetail(tvShowId: Int) = movieRepository.getTvShowDetail(tvShowId)

    override fun getMovieActors(movieId: Int) = movieRepository.getMovieActors(movieId)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getPeoples() = movieRepository.getPeoples()

}