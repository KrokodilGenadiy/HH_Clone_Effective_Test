package com.example.di

import com.example.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel {
        DetailsViewModel(get())
    }
}