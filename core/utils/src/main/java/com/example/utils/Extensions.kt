package com.example.utils

import android.content.res.Resources
import android.util.Patterns

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Int.convertPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

/** pPCword - Prural parent case(мн. ч. родительский падеж),
 * pCword - parent case(ед. ч. родительский падеж)
 * nCWord - nominative case(именнительный падеж) */
fun Int.getPluralAddition(pPCword: String, pCword: String, nCWord: String): String {
    val preLastDigit = this % 100 / 10

    if (preLastDigit == 1) {
        return pPCword
    }

    return when (this % 10) {
        1 -> nCWord
        2, 3, 4 -> pCword
        else -> pPCword
    }
}

fun Int.getMonthName(): String {
    return when (this) {
        in 1..12 -> {
            when (this) {
                1 -> "января"
                2 -> "февраля"
                3 -> "марта"
                4 -> "апреля"
                5 -> "мая"
                6 -> "июня"
                7 -> "июля"
                8 -> "aвгуста"
                9 -> "cентября"
                10 -> "октября"
                11 -> "ноября"
                12 -> "декабря"
                else -> "Invalid month number"
            }
        }
        else -> "Invalid month number"
    }
}

