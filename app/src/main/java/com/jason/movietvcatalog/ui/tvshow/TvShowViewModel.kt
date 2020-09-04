package com.jason.movietvcatalog.ui.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class TvShowViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val textQuery = MutableLiveData<String>()

    fun setSearchTv(text: String) {
        this.textQuery.value = text
    }

    var getTvshow = Transformations.switchMap(textQuery) { query ->
        movieUseCase.getAllTvShows(query).asLiveData()
    }
}