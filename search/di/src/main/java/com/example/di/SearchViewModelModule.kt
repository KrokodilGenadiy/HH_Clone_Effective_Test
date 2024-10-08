package com.example.di

import com.example.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val searchViewModelModule = module {

    viewModel {
        SearchViewModel(get(),get())
    }

}
