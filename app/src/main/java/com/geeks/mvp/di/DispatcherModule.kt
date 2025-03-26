package com.geeks.mvp.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher>(
        named("io")
    ) { Dispatchers.IO }
//    single { Dispatchers.IO }
    single { Dispatchers.Main }
    single { Dispatchers.Default }
}