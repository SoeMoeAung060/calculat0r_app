package com.soe.calculator.presentation.viewModel

data class CalculatorViewModelState(
    val firstNumber : String = "",
    val secondNumber : String = "",
    val operator : String = "",
    val expression : String = "",
    val result : String = ""

)