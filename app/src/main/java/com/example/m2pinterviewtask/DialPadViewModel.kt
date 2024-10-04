package com.example.m2pinterviewtask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DialPadViewModel() : ViewModel() {

    private val _currentInput = MutableLiveData<String>().apply { value = "$" }
    val currentInput: LiveData<String> get() = _currentInput

    fun onDigitPressed(digit: String) {
        Log.d("DialPadViewModel", "Digit pressed: $digit")
        val currentInputValue = _currentInput.value ?: "$"
        val updatedInput = if (currentInputValue == "$") {
            "$$digit"
        } else {
            "$currentInputValue$digit"
        }

        _currentInput.value = updatedInput
    }

    fun onBackspacePressed() {
        val currentInputValue = _currentInput.value ?: "$"
        _currentInput.value = if (currentInputValue.length > 1) {
            currentInputValue.substring(0, currentInputValue.length - 1)
        } else {
            "$"
        }
    }

}
