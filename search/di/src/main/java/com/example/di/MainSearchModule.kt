package com.example.di

import org.koin.dsl.module

val mainSearchModule = module {
    includes(searchInterModule, searchRepositoryModule, searchDataModule, searchViewModelModule)
}