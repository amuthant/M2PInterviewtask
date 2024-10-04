package com.example.m2pinterviewtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DialPadViewModelFactory(private val useCase: DialPadUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DialPadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DialPadViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
