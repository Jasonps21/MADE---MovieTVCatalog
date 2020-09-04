package com.jason.movietvcatalog.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase
import com.jason.movietvcatalog.core.utils.DataMapper
import kotlinx.coroutines.flow.map

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val textQuery = MutableLiveData<String>()

    private val dataMovie = MutableLiveData<List<Movie>>()

    fun setSearchMovie(text: String) {
        this.textQuery.value = text
    }

    var getMovies = Transformations.switchMap(textQuery) { query ->
        movieUseCase.getAllMovies(query).map {
            DataMapper.mapDomainToPresentationMovie(it.data!!)
        }.asLiveData()
    }
}