package com.example.data.favorites

import com.example.data.entities.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesInteractor {
    suspend fun insertFavoriteVacancy(vacancy: Vacancy)

    suspend fun deleteFavoriteVacancy(vacancyId: String)

    fun getFavoriteVacancy(vacancyId: String): Flow<Vacancy?>

    fun getFavoriteVacancies(): Flow<List<Vacancy>>

    fun getFavoriteVacanciesId(): Flow<List<String>>
}