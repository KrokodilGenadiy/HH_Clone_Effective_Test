package ru.practicum.android.diploma.di

import com.example.hh_clone_test.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SearchViewModel(get())
    }

}
