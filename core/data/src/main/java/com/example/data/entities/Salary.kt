package com.example.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Salary(
    val full: String?,
    val short: String?
): Parcelable