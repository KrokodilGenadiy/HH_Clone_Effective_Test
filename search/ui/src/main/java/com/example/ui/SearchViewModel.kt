package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entities.ApiResponse
import com.example.data.entities.Vacancy
import com.example.data.favorites.FavoritesInteractor
import com.example.domain.SearchInteractor
import com.example.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private val _apiResponseFlow = MutableStateFlow(
        ApiResponse(
            emptyList(),
            emptyList()
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val apiResponseFlow: StateFlow<ApiResponse> = _apiResponseFlow.mapLatest {
        getData()
        it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ApiResponse(emptyList(), emptyList()),
    )

    private fun getData() {
        viewModelScope.launch {
            searchInteractor.search().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        favoritesInteractor.getFavoriteVacanciesId().collect {
                           setFavorites(result.data.vacancies,it.toSet())
                        }
                        _apiResponseFlow.value = result.data
                    }

                    is Resource.Error -> {
                        // val errorMessage = result.error.asUiText()
                        //eventChannel.send(MoviesEvent.Error(errorMessage))
                    }
                }

            }
        }
    }

    private fun setFavorites(vacancies: List<Vacancy>, vacanciesIds: Set<String>) {
        vacancies.forEach {
            it.isFavorite = vacanciesIds.contains(it.vacancyId)
        }
    }

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
