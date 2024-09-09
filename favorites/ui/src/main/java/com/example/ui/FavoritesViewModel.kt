package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entities.Vacancy
import com.example.data.favorites.FavoritesInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(private val interactor: FavoritesInteractor) : ViewModel() {

    private val _vacanciesFlow = MutableStateFlow(
        emptyList<Vacancy>()
    )
    val vacanciesFlow: StateFlow<List<Vacancy>> = _vacanciesFlow.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            interactor.getFavoriteVacancies().collectLatest { result ->
                _vacanciesFlow.value = result
            }
        }
    }

    fun deleteFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            interactor.deleteFavoriteVacancy(vacancy.vacancyId)
        }
    }
}