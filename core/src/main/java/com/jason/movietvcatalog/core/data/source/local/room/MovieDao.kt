package com.jason.movietvcatalog.core.data.source.local.room

import androidx.room.*
import com.jason.movietvcatalog.core.data.source.local.entity.ActorEntity
import com.jason.movietvcatalog.core.data.source.local.entity.MovieEntity
import com.jason.movietvcatalog.core.data.source.local.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentities WHERE category = 'movie'")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE category = 'tvshow'")
    fun getTvShows(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM peopleentities")
    fun getPeoples(): Flow<List<PeopleEntity>>

    @Query("SELECT * FROM movieentities WHERE category = 'movie' AND isFavorite = 1 ")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE category = 'tvshow' AND isFavorite = 1 ")
    fun getFavoriteTvShows(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeoples(poeple: List<PeopleEntity>)

    @Update
    fun updateMovies(movie: MovieEntity)

    @Update
    suspend fun updateMovieDetail(movie: MovieEntity)

    @Query("SELECT * FROM actorentities WHERE movieId = :movieId")
    fun getActorByMovieId(movieId: Int): Flow<List<ActorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actor: List<ActorEntity>)
}