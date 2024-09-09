package com.example.data.api

import com.example.data.entities.ApiResponse
import retrofit2.http.GET

interface HeadHunterApi {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun search(): ApiResponse
}