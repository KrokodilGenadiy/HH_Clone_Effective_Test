package com.example.hh_clone_effective_test

import com.example.di.mainDetailsModule
import com.example.di.mainFavoritesModule
import com.example.di.mainSearchModule
import org.koin.dsl.module

val appModule = module {
    includes(mainSearchModule, mainFavoritesModule,mainDetailsModule)
}