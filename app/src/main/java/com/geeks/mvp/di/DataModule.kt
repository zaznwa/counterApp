package com.geeks.mvp.di

import com.geeks.mvp.data.datacourse.EmulateService
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.data.repository.CounterRepositoryImpl
import com.geeks.mvp.data.repository.MovieRepositoryImpl
import com.geeks.mvp.domain.repository.CounterRepository
import com.geeks.mvp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single { EmulateService() }
    single<CounterRepository> { CounterRepositoryImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), Dispatchers.IO) }
    single { MovieMapper() }
}
