package com.geeks.mvp.di

import com.geeks.mvp.presentation.viewmodel.FirstViewModel
import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FirstViewModel(
            incrementUseCase = get(),
            decrementUseCase = get(),
            getCountUseCase = get(),
        )
    }
    viewModel {
        MovieViewModel(
            moviesUseCase = get()
        )
    }
}
