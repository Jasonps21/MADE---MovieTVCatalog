package com.jason.movietvcatalog.discussions

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val discussionModule = module {
    viewModel { DiscussionViewModel(get()) }
}