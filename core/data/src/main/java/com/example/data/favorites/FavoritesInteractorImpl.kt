package com.example.data.favorites

import com.example.data.entities.Vacancy
import kotlinx.coroutines.flow.Flow

class FavoritesInteractorImpl(private val favoriteVacanciesRepository: FavoritesRepository) :
    FavoritesInteractor {

    override suspend fun insertFavoriteVacancy(vacancy: Vacancy) {
        favoriteVacanciesRepository.insertFavoriteVacancy(vacancy)
    }

    override suspend fun deleteFavoriteVacancy(vacancyId: String) {
        favoriteVacanciesRepository.deleteFavoriteVacancy(vacancyId)
    }

    override fun getFavoriteVacancy(vacancyId: String): Flow<Vacancy?> {
        return favoriteVacanciesRepository.getFavoriteVacancy(vacancyId)
    }

    override fun getFavoriteVacancies(): Flow<List<Vacancy>> {
        return favoriteVacanciesRepository.getFavoriteVacancies()
    }

    override fun getFavoriteVacanciesId(): Flow<List<String>> {
        return favoriteVacanciesRepository.getFavoriteVacanciesId()
    }

}