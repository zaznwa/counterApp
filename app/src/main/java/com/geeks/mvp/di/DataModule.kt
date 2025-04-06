package com.geeks.mvp.di

import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.data.repository.ApiRepositoryImpl
import com.geeks.mvp.domain.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get(), Dispatchers.IO, get()) }
    single { MovieMapper() }
}
