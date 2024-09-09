package com.example.di

import com.example.data.FavoritesRepositoryImpl
import com.example.data.FavoritesRepository
import org.koin.dsl.module

val favoritesRepositoryModule = module {
    single<FavoritesRepository> {
        FavoritesRepositoryImpl(get())
    }
}