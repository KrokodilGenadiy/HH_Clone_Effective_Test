package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.entities.Address
import com.example.data.entities.Experience
import com.example.data.entities.Salary

class RoomConverter {

    @TypeConverter
    fun fromStringToStringList(string: String): List<String> = string.split(",").map { it.trim() }

    @TypeConverter
    fun fromStringListToString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromExperienceToString(exp: Experience): String = exp.text + "," + exp.previewText

    @TypeConverter
    fun fromStringToExperience(string: String): Experience {
        val array = string.split(",").map { it.trim() }
        return Experience(array[1], array[0])
    }

    @TypeConverter
    fun fromAddressToString(address: Address): String =
        address.town + "," + address.street + "," + address.house

    @TypeConverter
    fun fromStringToAddress(string: String): Address {
        val array = string.split(",").map { it.trim() }
        return Address(house = array[2], street = array[1], town = array[0])
    }

    @TypeConverter
    fun fromSalaryToString(salary: Salary): String = salary.full + "," + salary.short

    @TypeConverter
    fun fromStringToSalary(string: String): Salary {
        val array = string.split(",").map { it.trim() }
        val full = array[0].ifBlank { null }
        val short = array[1].ifBlank { null }
        return Salary(full, short)
    }
}