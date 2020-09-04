package com.jason.movietvcatalog.core.data.source.remote

import android.util.Log
import com.jason.movietvcatalog.core.data.source.remote.response.ActorResponse
import com.jason.movietvcatalog.core.data.source.remote.response.MovieResponse
import com.jason.movietvcatalog.core.data.source.remote.response.TvResponse
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.data.source.remote.response.PeopleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(BuildConfig.GITHUB_TOKEN)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchMovies(BuildConfig.GITHUB_TOKEN, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShows(): Flow<ApiResponse<List<TvResponse>>> {

        return flow {
            try {
                val response = apiService.getTvshows(BuildConfig.GITHUB_TOKEN)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchTvs(query: String): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchTvs(BuildConfig.GITHUB_TOKEN, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(movieId: Int): Flow<ApiResponse<MovieResponse>> {

        return flow {
            try {
                val response = apiService.getMovieDetail(movieId, BuildConfig.GITHUB_TOKEN)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    fun getDetailTvShow(tvShowId: Int): Flow<ApiResponse<TvResponse>> {

        return flow {
            try {
                val response = apiService.getTvDetail(tvShowId, BuildConfig.GITHUB_TOKEN)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    fun getMovieActor(movieId: Int): Flow<ApiResponse<List<ActorResponse>>> {

        return flow {
            try {
                val response = apiService.getMovieCredits(movieId, BuildConfig.GITHUB_TOKEN)
                val dataArray = response.cast
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPeoples(): Flow<ApiResponse<List<PeopleResponse>>> {

        return flow {
            try {
                val response = apiService.getPeoples(BuildConfig.GITHUB_TOKEN)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}