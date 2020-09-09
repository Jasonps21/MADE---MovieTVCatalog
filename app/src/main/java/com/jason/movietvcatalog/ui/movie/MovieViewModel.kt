package com.jason.movietvcatalog.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val textQuery = MutableLiveData<String>()

    fun setSearchMovie(text: String) {
        this.textQuery.value = text
    }

    var getMovies = Transformations.switchMap(textQuery) { query ->
        movieUseCase.getAllMovies(query).asLiveData()
    }
}