package com.geeks.mvp.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single { Dispatchers.Main }
    single { Dispatchers.IO }
    single { Dispatchers.Default }
}