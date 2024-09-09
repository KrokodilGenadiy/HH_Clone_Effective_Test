package com.example.di

import com.example.data.FavoritesInteractorImpl
import com.example.data.FavoritesInteractor
import org.koin.dsl.module

val favoritesInteractorModule = module {
    factory<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }
}