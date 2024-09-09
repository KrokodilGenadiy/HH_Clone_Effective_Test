package com.example.di

import org.koin.dsl.module

val mainFavoritesModule = module {
    includes(favoritesDatabaseModule, favoritesInteractorModule, favoritesRepositoryModule, favoritesViewModelModule)
}