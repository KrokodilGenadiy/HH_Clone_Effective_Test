package com.example.hh_clone_test.search.data

import com.example.hh_clone_test.search.data.entities.ApiResponse
import com.example.hh_clone_test.search.domain.SearchRepository
import com.example.hh_clone_test.util.Resource
import com.example.hh_clone_test.util.error_handling.DataError
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val repository: SearchRepository) : SearchRepository {
    override suspend fun search(): Flow<Resource<ApiResponse, DataError.Network>> =
        repository.search()
}