package com.example.di

import com.example.data.favorites.FavoritesRepositoryImpl
import com.example.data.favorites.FavoritesRepository
import org.koin.dsl.module

val favoritesRepositoryModule = module {
    single<FavoritesRepository> {
        FavoritesRepositoryImpl(get())
    }
}