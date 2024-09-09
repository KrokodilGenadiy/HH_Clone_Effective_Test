package com.example.di

import com.example.data.favorites.FavoritesInteractorImpl
import com.example.data.favorites.FavoritesInteractor
import org.koin.dsl.module

val favoritesInteractorModule = module {
    factory<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }
}