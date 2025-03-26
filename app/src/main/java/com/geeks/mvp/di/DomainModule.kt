package com.geeks.mvp.di

import com.geeks.mvp.domain.usecases.DecrementUseCase
import com.geeks.mvp.domain.usecases.GetCountUseCase
import com.geeks.mvp.domain.usecases.IncrementUseCase
import com.geeks.mvp.domain.usecases.MovieUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { IncrementUseCase(get()) }
    factory { DecrementUseCase(get()) }
    factory { GetCountUseCase(get()) }
    factory { MovieUseCase(get()) }
}
