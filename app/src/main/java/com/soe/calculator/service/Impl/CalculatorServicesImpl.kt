package com.soe.calculator.service.Impl

import com.soe.calculator.service.module.CalculatorServices
import javax.inject.Inject
import kotlin.math.sqrt

class CalculatorServicesImpl @Inject constructor() : CalculatorServices {

    override suspend fun multiplication(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber * secondNumber
    }

    override suspend fun division(firstNumber: Double, secondNumber: Double): Double {
        return if (secondNumber != 0.0) {
            firstNumber / secondNumber
        } else{
            throw ArithmeticException("Division by zero is not allowed")
        }
    }

    override suspend fun addition(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber + secondNumber
    }

    override suspend fun subtraction(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber - secondNumber
    }

    override fun squareRoot(number: Double) : Double {
       return  if (number < 0){
            throw ArithmeticException("Cannot calculate square root of a negative number")
        }else{
            sqrt(number)
         }
    }


    override suspend fun percentage(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber % secondNumber
    }


    override suspend fun delete(firstNumber: Double) : Double {
        return firstNumber
    }

    override suspend fun clearOneDigit(firstNumber: String): String {
        return if(firstNumber.isNotEmpty()){
            firstNumber.dropLast(1)
        }else{
            "0"
        }
    }

    override suspend fun result(firstNumber: Double, secondNumber: Double, operator: String): Double {
        return when(operator){
            "+" -> addition(firstNumber, secondNumber)
            "-" -> subtraction(firstNumber, secondNumber)
            "*" -> multiplication(firstNumber, secondNumber)
            "/" -> division(firstNumber, secondNumber)
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}