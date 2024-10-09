package com.soe.calculator.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


fun formatNumberWithCommas(number: String): String {
    return try {
        // Parse the string to a number and apply formatting with commas
        val parsedNumber = number.toDouble()
        val formatter = DecimalFormat("#,###.##")
        formatter.format(parsedNumber)
    } catch (e: NumberFormatException) {
        // If there's any issue with parsing, return the original number string
        number
    }
}
//
fun formatNumberWithCommasAfterThreeDigits(number: String): String {
    try {
        val sb = StringBuilder(number.replace(",", "")) // Remove existing commas
        var i = 3
        while (i < sb.length) {
            sb.insert(i, ",") // Insert comma every 3 characters
            i += 4 // Move 4 positions forward (3 digits + the comma itself)
        }
        return sb.toString()
    }catch (e: Exception){
        return number
    }

}

//
//fun formatNumberWithCommasAfterThreeDigits(number: String): String {
//    // Remove any existing commas
//    val cleanedNumber = number.replace(",", "")
//
//    // Check if the cleaned number is valid (only digits)
//    if (cleanedNumber.isNotEmpty() && cleanedNumber.all { it.isDigit() }) {
//        val sb = StringBuilder(cleanedNumber)
//        var i = 3
//        while (i < sb.length) {
//            sb.insert(i, ",") // Insert comma every 3 digits
//            i += 4 // Move 4 positions forward (3 digits + the comma itself)
//        }
//        return sb.toString()
//    }
//
//    return number // If the number contains invalid characters, return it as is
//}