package com.jason.movietvcatalog.people

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val peopleModule = module {
    viewModel { PeopleViewModel(get()) }
}