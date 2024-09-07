package com.example.hh_clone_test.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_clone_test.search.data.entities.ApiResponse
import com.example.hh_clone_test.search.domain.SearchInteractor
import com.example.hh_clone_test.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(private val interactor: SearchInteractor): ViewModel() {

    private val _apiResponseFlow = MutableStateFlow(ApiResponse(emptyList(), emptyList()))
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
            interactor.search().collectLatest {  result ->
                when (result) {
                    is Resource.Success -> {
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
}