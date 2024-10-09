package com.soe.calculator.service.module


interface CalculatorServices {

    suspend fun multiplication(firstNumber: Double, secondNumber: Double): Double
    suspend fun division(firstNumber: Double, secondNumber: Double): Double
    suspend fun addition(firstNumber: Double, secondNumber: Double): Double
    suspend fun subtraction(firstNumber: Double, secondNumber: Double): Double
    fun squareRoot(number: Double) : Double
    suspend fun percentage(firstNumber: Double, secondNumber: Double): Double

    suspend fun delete(firstNumber: Double) : Double
    suspend fun clearOneDigit(firstNumber: String) : String
    suspend fun result(firstNumber: Double, secondNumber: Double, operator: String) : Double

}