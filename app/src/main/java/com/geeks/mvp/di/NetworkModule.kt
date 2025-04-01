package com.geeks.mvp.di

import android.util.Log
import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.data.repository.MovieRepositoryImpl
import com.geeks.mvp.domain.repository.MovieRepository
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofit(okHttpClient = get()) }
    single { provideApiService(retrofit = get()) }
    single { provideOkHttpClient(interceptor = get()) }
    single { provideLoggingInterceptor() }
    single { provideJson() }
    single<MovieRepository> {
        MovieRepositoryImpl(
            api = get(),
            io = get(named("io"))
            )
    }

}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.kinopoisk.dev/")
        .build()
}

fun provideApiService(retrofit: Retrofit): MovieApi {
    return retrofit.create(MovieApi::class.java)
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", "67JNJ5J-DZ4M4HC-H34CW05-3MSW04B")
                .build()
            Log.d("ololo", "Заголовки запроса: ${request.headers}")
            chain.proceed(request)
        }
        //.callTimeout(10L, TimeUnit.SECONDS)
        .connectTimeout(15L, TimeUnit.SECONDS)
        .readTimeout(105, TimeUnit.SECONDS)
        .writeTimeout(15L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideJson(): Json {
    return Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

