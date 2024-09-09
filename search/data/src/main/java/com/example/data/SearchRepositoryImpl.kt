package com.example.data

import com.example.data.entities.ApiResponse
import com.example.domain.SearchRepository
import com.example.utils.Resource
import com.example.utils.error_handling.DataError
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val repository: SearchRepository) : SearchRepository {
    override suspend fun search(): Flow<Resource<ApiResponse, DataError.Network>> =
        repository.search()
}