package com.jason.movietvcatalog.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase

class PeopleViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getPeople() = movieUseCase.getPeoples().asLiveData()
}