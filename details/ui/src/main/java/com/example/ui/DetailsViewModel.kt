package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FavoritesInteractor
import com.example.data.entities.Vacancy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val favoritesInteractor: FavoritesInteractor): ViewModel() {

    fun addToFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            favoritesInteractor.insertFavoriteVacancy(vacancy)
        }
    }

    fun deleteFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            favoritesInteractor.deleteFavoriteVacancy(vacancy.vacancyId)
        }
    }
}