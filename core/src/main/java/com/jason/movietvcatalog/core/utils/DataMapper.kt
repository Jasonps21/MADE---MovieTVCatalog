package com.jason.movietvcatalog.core.utils

import com.jason.movietvcatalog.core.data.source.local.entity.ActorEntity
import com.jason.movietvcatalog.core.data.source.local.entity.MovieEntity
import com.jason.movietvcatalog.core.data.source.local.entity.PeopleEntity
import com.jason.movietvcatalog.core.data.source.remote.response.ActorResponse
import com.jason.movietvcatalog.core.data.source.remote.response.MovieResponse
import com.jason.movietvcatalog.core.data.source.remote.response.PeopleResponse
import com.jason.movietvcatalog.core.data.source.remote.response.TvResponse
import com.jason.movietvcatalog.core.domain.model.Actor
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.core.domain.model.People

object DataMapper {
    fun mapResponsesToEntitiesMovie(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map { it ->
            val movie = MovieEntity(
                it.id!!.toInt(),
                it.title,
                it.posterPath,
                it.releaseDate,
                it.runtime,
                it.status,
                it.voteAverage,
                it.genres?.joinToString { it.name },
                it.overview,
                it.backdropPath,
                "movie"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesTvShow(input: List<TvResponse>): List<MovieEntity> {
        val tvShowList = ArrayList<MovieEntity>()
        input.map {
            val tvShow = MovieEntity(
                it.id!!.toInt(),
                it.name,
                it.poster_path,
                it.first_air_date,
                it.episode_run_time?.get(0),
                it.status,
                it.vote_average,
                "",
                it.overview,
                it.backdrop_path,
                "tvshow"
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapResponseToEntityMovie(input: MovieResponse) = MovieEntity(
        input.id!!.toInt(),
        input.title,
        input.posterPath,
        input.releaseDate,
        input.runtime,
        input.status,
        input.voteAverage,
        input.genres?.joinToString { it.name },
        input.overview,
        input.backdropPath,
        "movie"
    )

    fun mapResponseToEntityTvShow(input: TvResponse) = MovieEntity(
        input.id!!.toInt(),
        input.name,
        input.poster_path,
        input.first_air_date,
        input.episode_run_time?.get(0),
        input.status,
        input.vote_average,
        input.genres?.joinToString { it.name },
        input.overview,
        input.backdrop_path,
        "tvshow"
    )

    fun mapResponsesToEntitiesActor(input: List<ActorResponse>, movieId: Int): List<ActorEntity> {
        val actorList = ArrayList<ActorEntity>()
        input.map {
            val actor =  ActorEntity(
                0,
                it.character,
                it.profile_path,
                it.name,
                movieId
            )
            actorList.add(actor)
        }
        return actorList
    }

    fun mapResponsesToEntitiesPeople(input: List<PeopleResponse>): List<PeopleEntity> {
        val peopleList = ArrayList<PeopleEntity>()
        input.map {
            val people =  PeopleEntity(
                id = it.id,
                name = it.name,
                profilePath = it.profilePath,
                knownForDepartment = it.knownForDepartment,
                popularity = it.popularity
            )
            peopleList.add(people)
        }
        return peopleList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.name,
                it.posterPath,
                it.releaseDate,
                it.runtime,
                it.status,
                it.vote,
                it.genre,
                it.overview,
                it.backdrop,
                "movie",
                isFavorite = it.isFavorite
            )
        }

    fun mapEntitiesToDomainActor(movieId: Int, input: List<ActorEntity>): List<Actor> =
        input.map {
            Actor(
                0,
                it.character,
                it.profilePath,
                it.name,
                movieId
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        name = input.name,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        runtime = input.runtime,
        status = input.status,
        vote = input.vote,
        genre = input.genre,
        overview = input.overview,
        backdrop = input.backdrop,
        category = input.category,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: MovieEntity) = Movie(
        id = input.id,
        name = input.name,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        runtime = input.runtime,
        status = input.status,
        vote = input.vote,
        genre = input.genre,
        overview = input.overview,
        backdrop = input.backdrop,
        category = input.category,
        isFavorite = input.isFavorite
    )

    fun mapEntitiesToDomainPeople(input: List<PeopleEntity>): List<People> =
        input.map {
            People(
                id = it.id,
                name = it.name,
                profilePath = it.profilePath,
                knownForDepartment = it.knownForDepartment,
                popularity = it.popularity
            )
        }
}