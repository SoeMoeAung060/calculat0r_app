package com.soe.calculator.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soe.calculator.model.buttonRow0
import com.soe.calculator.model.buttonRow1
import com.soe.calculator.model.buttonRow2
import com.soe.calculator.model.buttonRow3
import com.soe.calculator.model.buttonRow4
import com.soe.calculator.presentation.ui.theme.CalculatorTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.soe.calculator.presentation.viewModel.CalculatorViewModel


@Composable
fun CalculatorRoute(modifier: Modifier = Modifier) {
    CalculatorScreen()
}


@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color(0xFF17171C)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 14.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    val displayText = buildString {
                        append(uiState.firstNumber)
                        if (uiState.operator.isNotBlank()) {
                            append(uiState.operator)
                        }
                        append(uiState.secondNumber)
                    }

                    // Calculate the font size based on the total length of the display text
                    val fontSize = when {
                        displayText.length == 8 -> 75.sp
                        displayText.length == 9 -> 70.sp
                        displayText.length == 10 -> 65.sp
                        displayText.length == 11 -> 60.sp
                        displayText.length >= 12 -> 55.sp
                        else -> 80.sp
                    }

                    Text(
                        text = uiState.firstNumber + (uiState.operator.takeIf { it.isNotBlank() }
                            ?: "") + uiState.secondNumber,
                        fontSize = fontSize,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        maxLines = 2,
                        lineHeight = 80.sp,
                        textAlign = TextAlign.Right
                    )
                }


                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    val buttonRows =
                        listOf(buttonRow0, buttonRow1, buttonRow2, buttonRow3, buttonRow4)

                    buttonRows.reversed().forEach { row ->
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            row.forEach { item ->
                                CalculatorButton(
                                    text = item.text,
                                    color = item.color,
                                    onClick = {
                                        when (item.text) {
                                            "C" -> viewModel.onClearClick()
                                            "=" -> viewModel.calculateResult()
                                            "+" -> viewModel.onOperatorClick("+")
                                            "-" -> viewModel.onOperatorClick("-")
                                            "x" -> viewModel.onOperatorClick("*")
                                            "÷" -> viewModel.onOperatorClick("/")
                                            "%" -> viewModel.onOperatorClick("%")
                                            "√" -> viewModel.onOperatorClick("√")
                                            "." -> viewModel.onDecimal()
                                            "⌫" -> viewModel.onDeleteAll()
                                            else -> viewModel.onNumberClick(item.text.toInt())
                                        }
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Long
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(80.dp),
        shape = RoundedCornerShape(24.dp),
        contentPadding = ButtonDefaults.ContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(color),
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 32.sp)
    }
}


@Preview(showBackground = true)
@Composable
private fun CalculatorButtonPreview() {
    CalculatorTheme {
        CalculatorButton(
            "1",
            color = 0xFF2E2F38,
            onClick = {}
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorTheme {
        CalculatorScreen(
        )
    }

}


