package com.example.domain

import com.example.data.entities.ApiResponse
import com.example.utils.Resource
import com.example.utils.error_handling.DataError
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun search(): Flow<Resource<ApiResponse, DataError.Network>>
}