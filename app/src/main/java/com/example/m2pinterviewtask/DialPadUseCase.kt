package com.example.m2pinterviewtask

class DialPadUseCase(private val repository: DialPadRepository) {

    fun addDigit(digit: String): String {
        return repository.addDigit(digit)
    }

    fun removeLastDigit(): String {
        return repository.removeLastDigit()
    }
}