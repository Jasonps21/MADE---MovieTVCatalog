package com.jason.movietvcatalog.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getMoviesFavorite() = movieUseCase.getMoviesFavorite().asLiveData()
}