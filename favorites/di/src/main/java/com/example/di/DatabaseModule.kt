package com.example.di

import androidx.room.Room
import com.example.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val favoritesDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}