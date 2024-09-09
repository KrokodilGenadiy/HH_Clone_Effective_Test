package com.example.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.adapter_interface.DelegateAdapterItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "favorites_vacancies")
data class Vacancy(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    @SerializedName("id")
    val vacancyId: String,
    @ColumnInfo(name = "address")
    val address: Address,
    @ColumnInfo(name = "appliedNumber")
    val appliedNumber: Int?,
    @ColumnInfo(name = "company_name")
    val company: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "experience")
    val experience: Experience,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "lookingNumber")
    val lookingNumber: Int?,
    @ColumnInfo(name = "publishedDate")
    val publishedDate: String,
    @ColumnInfo(name = "questions")
    val questions: List<String>,
    @ColumnInfo(name = "responsibilities")
    val responsibilities: String,
    @ColumnInfo(name = "salary")
    val salary: Salary,
    @ColumnInfo(name = "schedules")
    val schedules: List<String>,
    @ColumnInfo(name = "title")
    val title: String
) : Parcelable, DelegateAdapterItem {
    override fun id() = vacancyId

    override fun content() = responsibilities+vacancyId
}