package com.example.di

import com.example.data.SearchInteractorImpl
import com.example.domain.SearchInteractor
import org.koin.dsl.module

val searchInterModule = module {
    factory<SearchInteractor> {
        SearchInteractorImpl(get(), get())
    }
}
