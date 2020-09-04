package com.jason.movietvcatalog.di

import com.jason.movietvcatalog.core.domain.usecase.MovieInteractor
import com.jason.movietvcatalog.core.domain.usecase.MovieUseCase
import com.jason.movietvcatalog.ui.detail.DetailViewModel
import com.jason.movietvcatalog.ui.favorite.movie.FavoriteMovieViewModel
import com.jason.movietvcatalog.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.jason.movietvcatalog.ui.movie.MovieViewModel
import com.jason.movietvcatalog.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {MovieInteractor(get())}
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}