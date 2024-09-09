package com.example.data

import com.example.data.database.AppDatabase
import com.example.data.entities.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase,
) : FavoritesRepository {

    override suspend fun insertFavoriteVacancy(vacancy: Vacancy) {
        appDatabase.vacancyDao().insertFavoriteVacancy(vacancy)
    }

    override suspend fun deleteFavoriteVacancy(vacancyId: String) {
        appDatabase.vacancyDao().deleteFavoriteVacancy(vacancyId)
    }

    override fun getFavoriteVacancy(vacancyId: String): Flow<Vacancy?> = flow {
        val favoriteVacancyFromDataBase = appDatabase.vacancyDao().getFavoriteVacancy(vacancyId)
        emit(favoriteVacancyFromDataBase)
    }.flowOn(Dispatchers.IO)


    override fun getFavoriteVacancies(): Flow<List<Vacancy>> = flow {
        val favoriteVacanciesList = appDatabase.vacancyDao().getFavoriteVacancies()
        emit(favoriteVacanciesList)
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteVacanciesId(): Flow<List<String>> = flow {
        val favoriteVacanciesIdList = appDatabase.vacancyDao().getFavoriteVacanciesId()
        emit(favoriteVacanciesIdList)
    }.flowOn(Dispatchers.IO)
}
