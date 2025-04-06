package com.geeks.mvp.di

import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MovieViewModel(
            moviesUseCase =get(),
            io = Dispatchers.IO,
        )
    }
}
