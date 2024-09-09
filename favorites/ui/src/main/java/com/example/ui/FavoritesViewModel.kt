package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entities.Vacancy
import com.example.data.FavoritesInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(private val interactor: FavoritesInteractor): ViewModel() {

    private val _vacanciesFlow = MutableStateFlow(
        emptyList<Vacancy>()
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    val vacanciesFlow: StateFlow<List<Vacancy>> = _vacanciesFlow.mapLatest {
        getData()
        it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    private fun getData() {
        viewModelScope.launch {
            interactor.getFavoriteVacancies().collectLatest { result ->
                _vacanciesFlow.value = result
            }
        }
    }

    fun addToFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            interactor.insertFavoriteVacancy(vacancy)
        }
    }

    fun deleteFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            interactor.deleteFavoriteVacancy(vacancy.vacancyId)
        }
    }
}