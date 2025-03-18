package com.geeks.mvp

import android.app.Application
import com.geeks.mvp.di.dataModule
import com.geeks.mvp.di.domainModule
import com.geeks.mvp.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, domainModule, viewModelModule)
        }
    }

}