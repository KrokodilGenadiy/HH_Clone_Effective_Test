package com.example.di

import org.koin.dsl.module

val mainDetailsModule = module {
    includes(detailsViewModelModule)
}