package com.geeks.mvp.di

import com.geeks.mvp.data.repository.CounterRepositoryImpl
import com.geeks.mvp.domain.repository.CounterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindCounterRepository(counterRepositoryImpl: CounterRepositoryImpl): CounterRepository

}