package com.geeks.mvp.di

import com.geeks.mvp.domain.usecases.GetMovieUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetMovieUseCase(
            apiRepository = get(),
            movieMapper = get(),
        )
    }
}
