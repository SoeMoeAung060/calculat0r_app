package com.soe.calculator.model


data class Collections(
    val text : String,
    val color : Long,
    val type: ButtonType
)

enum class ButtonType {
    NUMBER,
    OPERATOR,
    FUNCTION
}

val buttonRow0 = listOf(
    Collections(".", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("0", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("⌫", 0xFF2E2F38, ButtonType.OPERATOR),
    Collections("=", 0xFF4B5EFC, ButtonType.FUNCTION),
)

val buttonRow1 = listOf(
    Collections("1", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("2", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("3", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("+", 0xFF4B5EFC, ButtonType.OPERATOR),
)

val buttonRow2 = listOf(
    Collections("4", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("5", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("6", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("-", 0xFF4B5EFC, ButtonType.OPERATOR),
)

val buttonRow3 = listOf(
    Collections("7", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("8", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("9", 0xFF2E2F38, ButtonType.NUMBER),
    Collections("x", 0xFF4B5EFC, ButtonType.OPERATOR),
)

val buttonRow4 = listOf(
    Collections("C", 0xFF4E505F, ButtonType.FUNCTION),
    Collections("√", 0xFF4E505F, ButtonType.FUNCTION),
    Collections("%", 0xFF4E505F, ButtonType.FUNCTION),
    Collections("÷", 0xFF4B5EFC, ButtonType.OPERATOR),
)
