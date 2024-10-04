package com.example.m2pinterviewtask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialPadViewModel(private val dialPadUseCase: DialPadUseCase) : ViewModel() {

    private val _currentInput = MutableLiveData<String>()
    val currentInput: LiveData<String> = _currentInput

    fun onDigitPressed(digit: String) {
        Log.e("DialPadViewModel", "Digit pressed: $digit") // Log statement
        val updatedInput = dialPadUseCase.addDigit(digit)
        _currentInput.value = updatedInput
    }
    fun onBackspacePressed() {
        val updatedInput = dialPadUseCase.removeLastDigit()
        _currentInput.value = updatedInput
    }
}