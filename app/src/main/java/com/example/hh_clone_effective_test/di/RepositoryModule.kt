package ru.practicum.android.diploma.di

import com.example.hh_clone_test.search.data.SearchRepositoryImpl
import com.example.hh_clone_test.search.domain.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }

}
