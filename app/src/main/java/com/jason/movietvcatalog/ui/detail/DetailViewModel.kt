package com.jason.movietvcatalog.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movieDetail = Transformations.switchMap(movieId) { mMovieId ->
        movieUseCase.getMovieDetail(mMovieId).asLiveData()
    }

    var tvShowDetail = Transformations.switchMap(movieId) { mTvShowId ->
        movieUseCase.getTvShowDetail(mTvShowId).asLiveData()
    }

    var actors = Transformations.switchMap(movieId) { mMovieId ->
        movieUseCase.getMovieActors(mMovieId).asLiveData()
    }

//    var actors = null

    fun setFavorite(category: Int) {
        val resource = when (category) {
            R.string.movie -> movieDetail.value
            R.string.tvshow -> tvShowDetail.value
            else -> null
        }
        if (resource != null) {
            val movieDetail = resource.data
            if (movieDetail != null) {
                val newState = !movieDetail.isFavorite
                movieUseCase.setFavoriteMovie(movieDetail, newState)
            }
        }
    }
}