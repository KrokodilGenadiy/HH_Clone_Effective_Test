package com.example.hh_clone_test.util

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