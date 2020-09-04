package com.jason.movietvcatalog.discussions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class DiscussionViewModel (movieUseCase: MovieUseCase): ViewModel(){
    val movie = movieUseCase.getAllMovies("").asLiveData()
}