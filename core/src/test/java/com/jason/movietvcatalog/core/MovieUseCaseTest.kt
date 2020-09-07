package com.jason.movietvcatalog.core

import com.jason.movietvcatalog.core.domain.repository.IMovieRepository
import com.jason.movietvcatalog.core.domain.usecase.MovieInteractor
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteractor(movieRepository)
    }

    @Test
    fun `should get data from repository`() {
        movieUseCase.getAllMovies("")

        verify(movieRepository).getAllMovies("")
        verifyNoMoreInteractions(movieRepository)
    }
}