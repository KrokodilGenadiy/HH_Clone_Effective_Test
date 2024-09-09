package com.example.di

import com.example.ui.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesViewModelModule = module {
    viewModel {
        FavoritesViewModel(get())
    }
}