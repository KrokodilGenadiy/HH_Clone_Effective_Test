package com.example.hh_clone_test.search.domain

import com.example.hh_clone_test.search.data.entities.ApiResponse
import com.example.hh_clone_test.util.Resource
import com.example.hh_clone_test.util.error_handling.DataError
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun search(): Flow<Resource<ApiResponse, DataError.Network>>
}