package com.jason.movietvcatalog.core.data.source.local

import com.jason.movietvcatalog.core.data.source.local.entity.ActorEntity
import com.jason.movietvcatalog.core.data.source.local.entity.MovieEntity
import com.jason.movietvcatalog.core.data.source.local.entity.PeopleEntity
import com.jason.movietvcatalog.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = mMovieDao.getMovies()

    fun getAllTvShows(): Flow<List<MovieEntity>> = mMovieDao.getTvShows()

    fun getPeoples(): Flow<List<PeopleEntity>> = mMovieDao.getPeoples()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mMovieDao.getFavoriteMovies()

    fun getFavoriteTvShows(): Flow<List<MovieEntity>> = mMovieDao.getFavoriteTvShows()

    fun getMovieDetail(movieId: Int): Flow<MovieEntity> =
        mMovieDao.getMovieById(movieId)

    fun getAllActorsByMovie(movieId: Int): Flow<List<ActorEntity>> =
        mMovieDao.getActorByMovieId(movieId)

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    suspend fun insertActors(actors: List<ActorEntity>) = mMovieDao.insertActors(actors)

    suspend fun insertPeoples(peoples: List<PeopleEntity>) = mMovieDao.insertPeoples(peoples)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateMovies(movie)
    }

    suspend fun updateMovie(movie: MovieEntity) {
        mMovieDao.updateMovieDetail(movie)
    }
}