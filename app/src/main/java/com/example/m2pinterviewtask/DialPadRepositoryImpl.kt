package com.example.m2pinterviewtask

class DialPadRepositoryImpl : DialPadRepository {

    private var currentInput: String = ""

    override fun addDigit(digit: String): String {
        currentInput += digit
        return currentInput
    }

    override fun removeLastDigit(): String {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
        }
        return currentInput
    }
}