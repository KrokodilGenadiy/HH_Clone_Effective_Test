package com.example.hh_clone_test.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_clone_test.search.data.entities.ApiResponse
import com.example.hh_clone_test.search.domain.SearchInteractor
import com.example.hh_clone_test.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val interactor: SearchInteractor): ViewModel() {

    private val _apiResponseFlow = MutableStateFlow(ApiResponse(emptyList(), emptyList()))
    val apiResponseFlow: StateFlow<ApiResponse> = _apiResponseFlow.asStateFlow()

    fun getData() {
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