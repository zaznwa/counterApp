package com.geeks.mvp.di

import com.geeks.mvp.presentation.viewmodel.FirstViewModel
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
}
