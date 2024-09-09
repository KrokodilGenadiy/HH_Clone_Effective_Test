package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.entities.Vacancy

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteVacancy(vacancy: Vacancy)

    @Query("DELETE FROM favorites_vacancies WHERE vacancy_id=:vacancyId")
    suspend fun deleteFavoriteVacancy(vacancyId: String)

    @Query("SELECT * FROM favorites_vacancies")
    suspend fun getFavoriteVacancies(): List<Vacancy>

    @Query("SELECT * FROM favorites_vacancies WHERE vacancy_id=:vacancyId")
    suspend fun getFavoriteVacancy(vacancyId: String): Vacancy?

    @Query("SELECT vacancy_id FROM favorites_vacancies")
    suspend fun getFavoriteVacanciesId(): List<String>

}
