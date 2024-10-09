package com.soe.calculator.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soe.calculator.service.module.CalculatorServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorServices: CalculatorServices
) : ViewModel() {

    private val _uiStat = MutableStateFlow(CalculatorViewModelState())
    val uiState = _uiStat.asStateFlow()


    companion object {
        private const val MAX_NUMBER_LENGTH = 9
    }


    fun onNumberClick(number: Int) {
        if (_uiStat.value.operator.isEmpty()) {
//            if (_uiStat.value.firstNumber.length >= MAX_NUMBER_LENGTH || _uiStat.value.expression.length >= MAX_NUMBER_LENGTH) {
//                return
//            }
            _uiStat.update { currentState ->

                currentState.copy(
                    firstNumber = if (currentState.firstNumber.startsWith("0")) {
                        number.toString()
                    }else{
                        currentState.firstNumber + number.toString()
                    }
                )
            }


        } else {
            _uiStat.update { currentState ->
                currentState.copy(
                    secondNumber = if (currentState.secondNumber.startsWith("0")) {
                        number.toString()
                    }else{
                        currentState.secondNumber + number.toString()
                    }
                )
            }
        }

    }


    fun onOperatorClick(operator: String) {
        viewModelScope.launch {
            _uiStat.update { currentState ->
                currentState.copy(
                    operator = operator,
                )
            }
        }
    }

    fun onClearClick() {
        viewModelScope.launch {
            _uiStat.update { currentState ->
                when {
                    currentState.secondNumber.isNotBlank() -> currentState.copy(
                        secondNumber = currentState.secondNumber.dropLast(1)
                    )

                    currentState.operator.isNotBlank() -> currentState.copy(
                        operator = ""
                    )

                    currentState.firstNumber.isNotBlank() -> currentState.copy(
                        firstNumber = currentState.firstNumber.dropLast(1)
                    )

                    else -> currentState
                }
            }
        }
    }

    fun onDeleteAll() {
        viewModelScope.launch {
            _uiStat.update { currentState ->
                // Check if any values exist, and if so, clear them
                if (currentState.firstNumber.isNotBlank() ||
                    currentState.secondNumber.isNotBlank() ||
                    currentState.operator.isNotBlank()
                ) {

                    currentState.copy(
                        firstNumber = "",
                        secondNumber = "",
                        operator = ""
                    )
                } else {
                    currentState
                }
            }
        }
    }

    fun onDecimal() {
        viewModelScope.launch {
            _uiStat.update { currentState ->
                if (
                    currentState.operator.isBlank()
                ) {
                    if (!currentState.firstNumber.contains(".")) {
                        currentState.copy(
                            firstNumber = currentState.firstNumber + "."
                        )
                    } else {
                        currentState
                    }

                } else {
                    if (!currentState.secondNumber.contains(".")) {
                        currentState.copy(
                            secondNumber = currentState.secondNumber + "."
                        )
                    } else {
                        currentState
                    }
                }
            }
        }
    }


    fun calculateResult() {
        viewModelScope.launch {
            _uiStat.update { currentState ->

                // Safely convert firstNumber and displayText to integers
                val firstNum = currentState.firstNumber.toDoubleOrNull()
                val secondNum = currentState.secondNumber.toDoubleOrNull()

                // Log the values for debugging
                Log.d(
                    "CalculatorViewModel",
                    "First Number: $firstNum, Second Number: $secondNum, Operator: ${currentState.operator}"
                )

                var result: Double? = null


                // Check if both numbers are valid
                if (firstNum != null || secondNum != null) {

                    // Handle the case where the number is not valid (e.g., show an error or return current state)
                    result = when (currentState.operator) {
                        "+" -> calculatorServices.addition(
                            currentState.firstNumber.toDouble(),
                            currentState.secondNumber.toDouble()
                        )

                        "-" -> calculatorServices.subtraction(
                            currentState.firstNumber.toDouble(),
                            currentState.secondNumber.toDouble()
                        )

                        "*" -> calculatorServices.multiplication(
                            currentState.firstNumber.toDouble(),
                            currentState.secondNumber.toDouble()
                        )

                        "/" -> calculatorServices.division(
                            currentState.firstNumber.toDouble(),
                            currentState.secondNumber.toDouble()
                        )

                        "%" -> calculatorServices.percentage(
                            currentState.firstNumber.toDouble(),
                            currentState.secondNumber.toDouble()
                        )

                        "√" -> calculatorServices.squareRoot(
                            currentState.firstNumber.toDouble()
                        )

                        else -> return@launch
                    }
                }

//                 Convert the result to a string with appropriate formatting
                val formattedResult = result?.let {
                    if (it % 1 == 0.0) {
                        it.toInt().toString()
                    } else {
                        it.toString()
                    }
                }



                currentState.copy(
                    firstNumber = formattedResult.toString().take(15) ,
                    secondNumber = "",
                    operator = ""
                )

            }
        }

    }


    fun onCalculate() {
        viewModelScope.launch {
            _uiStat.update { currentState ->

                val result = calculateResult()

                currentState.copy(
                    result = result.toString(),
                    firstNumber = "",
                    secondNumber = "",
                    operator = ""
                )
            }
        }
    }


}