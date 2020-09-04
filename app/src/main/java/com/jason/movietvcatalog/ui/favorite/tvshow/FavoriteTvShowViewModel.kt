package com.jason.movietvcatalog.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class FavoriteTvShowViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getTvShowsFavorite() = movieUseCase.getTvShowsFavorite().asLiveData()
}