package com.example.di

import com.example.data.SearchRepositoryImpl
import com.example.domain.SearchRepository
import org.koin.dsl.module

val searchRepositoryModule = module {

    single<SearchRepository> {
       SearchRepositoryImpl(get())
    }

}
