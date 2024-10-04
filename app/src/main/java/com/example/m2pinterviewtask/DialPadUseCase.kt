package com.example.m2pinterviewtask

class DialPadUseCase(private val repository: DialPadRepository) {

    private var currentDigits = ""

    fun addDigit(digit: String): String {
        currentDigits += digit
        return "$currentDigits"
    }

    fun removeLastDigit(): String {
        if (currentDigits.isNotEmpty()) {
            currentDigits = currentDigits.dropLast(1)
        }
        return if (currentDigits.isEmpty()) "" else currentDigits
    }
}
