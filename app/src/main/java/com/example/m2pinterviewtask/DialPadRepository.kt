package com.example.m2pinterviewtask

interface DialPadRepository {
    fun addDigit(digit: String): String
    fun removeLastDigit(): String
}
