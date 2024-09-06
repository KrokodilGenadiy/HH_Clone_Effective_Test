package ru.practicum.android.diploma.di

import android.content.Context
import com.example.hh_clone_test.core.api.HeadHunterApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single<HeadHunterApi> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(HeadHunterApi::class.java)
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        })
        .build()
    }

    factory { Gson() }

}
