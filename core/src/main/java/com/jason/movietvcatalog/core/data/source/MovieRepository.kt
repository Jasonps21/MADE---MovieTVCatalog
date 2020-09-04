package com.jason.movietvcatalog.core.data.source

import com.jason.movietvcatalog.core.data.source.local.LocalDataSource
import com.jason.movietvcatalog.core.data.source.remote.ApiResponse
import com.jason.movietvcatalog.core.data.source.remote.RemoteDataSource
import com.jason.movietvcatalog.core.data.source.remote.response.ActorResponse
import com.jason.movietvcatalog.core.data.source.remote.response.MovieResponse
import com.jason.movietvcatalog.core.data.source.remote.response.PeopleResponse
import com.jason.movietvcatalog.core.data.source.remote.response.TvResponse
import com.jason.movietvcatalog.core.domain.model.Actor
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.core.domain.model.People
import com.jason.movietvcatalog.core.domain.repository.IMovieRepository
import com.jason.movietvcatalog.core.utils.AppExecutors
import com.jason.movietvcatalog.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(query: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true
//                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return if (query.isNotEmpty()) {
                    remoteDataSource.getSearchMovies(query)
                } else {
                    remoteDataSource.getAllMovies()
                }
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                localDataSource.insertMovies(DataMapper.mapResponsesToEntitiesMovie(data))
            }
        }.asFlow()
    }

    override fun getAllTvShows(query: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllTvShows().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true
//                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> {
                return if (query.isNotEmpty()) {
                    remoteDataSource.getSearchTvs(query)
                } else {
                    remoteDataSource.getAllTvShows()
                }
            }

            override suspend fun saveCallResult(data: List<TvResponse>) {
                localDataSource.insertMovies(DataMapper.mapResponsesToEntitiesTvShow(data))
            }

        }.asFlow()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(movieId).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.status == null || data.runtime == null || data.genre == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override suspend fun saveCallResult(data: MovieResponse) {
                localDataSource.updateMovie(DataMapper.mapResponseToEntityMovie(data))
            }

        }.asFlow()
    }

    override fun getTvShowDetail(tvShowId: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, TvResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(tvShowId).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.status == null || data.runtime == null || data.genre == null

            override suspend fun createCall(): Flow<ApiResponse<TvResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId)

            override suspend fun saveCallResult(data: TvResponse) {
                localDataSource.updateMovie(DataMapper.mapResponseToEntityTvShow(data))
            }
        }.asFlow()
    }

    override fun getMovieActors(movieId: Int): Flow<Resource<List<Actor>>> {
        return object : NetworkBoundResource<List<Actor>, List<ActorResponse>>() {
            override fun loadFromDB(): Flow<List<Actor>> {
                return localDataSource.getAllActorsByMovie(movieId).map {
                    DataMapper.mapEntitiesToDomainActor(movieId, it)
                }
            }

            override fun shouldFetch(data: List<Actor>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ActorResponse>>> =
                remoteDataSource.getMovieActor(movieId)

            override suspend fun saveCallResult(data: List<ActorResponse>) {
                localDataSource.insertActors(DataMapper.mapResponsesToEntitiesActor(data, movieId))
            }
        }.asFlow()
    }

    override fun getMoviesFavorite(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getTvShowsFavorite(): Flow<List<Movie>> {
        return localDataSource.getFavoriteTvShows().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun getPeoples(): Flow<Resource<List<People>>> {
        return object : NetworkBoundResource<List<People>, List<PeopleResponse>>() {
            override fun loadFromDB(): Flow<List<People>> {
                return localDataSource.getPeoples().map {
                    DataMapper.mapEntitiesToDomainPeople(it)
                }
            }

            override fun shouldFetch(data: List<People>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PeopleResponse>>> =
                remoteDataSource.getPeoples()

            override suspend fun saveCallResult(data: List<PeopleResponse>) {
                localDataSource.insertPeoples(DataMapper.mapResponsesToEntitiesPeople(data))
            }
        }.asFlow()
    }

}