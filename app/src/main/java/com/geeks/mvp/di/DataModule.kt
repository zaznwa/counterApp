package com.geeks.mvp.di

import com.geeks.mvp.data.datacourse.EmulateService
import com.geeks.mvp.data.repository.CounterRepositoryImpl
import com.geeks.mvp.domain.repository.CounterRepository
import org.koin.dsl.module

val dataModule = module {
    single { EmulateService() }
    single<CounterRepository> { CounterRepositoryImpl(get()) }
}
