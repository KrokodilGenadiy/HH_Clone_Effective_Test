package com.example.data.entities

data class ApiResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)