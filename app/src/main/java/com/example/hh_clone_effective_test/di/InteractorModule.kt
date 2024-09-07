package ru.practicum.android.diploma.di

import com.example.hh_clone_test.search.data.SearchInteractorImpl
import com.example.hh_clone_test.search.domain.SearchInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory<SearchInteractor> {
        SearchInteractorImpl(get(),get())
    }
}
