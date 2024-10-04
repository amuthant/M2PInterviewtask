package com.example.m2pinterviewtask.dialpad

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.example.m2pinterviewtask.databinding.ActivityDialPadBinding
import com.example.m2pinterviewtask.DialPadRepositoryImpl
import com.example.m2pinterviewtask.DialPadUseCase
import com.example.m2pinterviewtask.DialPadViewModel
import com.example.m2pinterviewtask.DialPadViewModelFactory

/*
class DialPadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialPadBinding
    private lateinit var viewModel: DialPadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDialPadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DialPadRepositoryImpl()
        val useCase = DialPadUseCase(repository)
        viewModel = ViewModelProvider(this, DialPadViewModelFactory(useCase)).get(DialPadViewModel::class.java)

        // Observe the LiveData from the ViewModel
        viewModel.currentInput.observe(this) { input ->
            binding.appCompatEditText.setText(input) // Update UI when data changes
        }
        binding.btnConfirm.setOnClickListener {
            // Get the text from the button
            val buttonText = binding.appCompatEditText.text.toString()
            Log.d("DialPadActivity", "Confirm button clicked: $buttonText")
        }
        setupCustomKeyboard()
    }

    private fun setupButtonListener(button: AppCompatTextView, digit: String) {
        button.setOnClickListener {
            Log.d("DialPadActivity", "$digit clicked") // Use Log.d for debug
            viewModel.onDigitPressed(digit)
        }
    }

    private fun setupCustomKeyboard() {
        setupButtonListener(binding.btn1, "1")
        setupButtonListener(binding.btn2, "2")
        setupButtonListener(binding.btn3, "3")
        setupButtonListener(binding.btn4, "4")
        setupButtonListener(binding.btn5, "5")
        setupButtonListener(binding.btn6, "6")
        setupButtonListener(binding.btn7, "7")
        setupButtonListener(binding.btn8, "8")
        setupButtonListener(binding.btn9, "9")
        setupButtonListener(binding.btnDot, ".")

        binding.btnBackspace.setOnClickListener {
            Log.d("DialPadActivity", "Backspace clicked")
            viewModel.onBackspacePressed()
        }
    }
}
*/
class DialPadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialPadBinding
    private lateinit var viewModel: DialPadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDialPadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DialPadRepositoryImpl()
        val useCase = DialPadUseCase(repository)
        viewModel = ViewModelProvider(this, DialPadViewModelFactory(useCase)).get(DialPadViewModel::class.java)

        // Observe the LiveData from the ViewModel
        viewModel.currentInput.observe(this) { input ->
            binding.appCompatEditText.setText(input) // Update UI when data changes
        }

        binding.btnConfirm.setOnClickListener {
            // Get the numeric value from the CurrencyEditText
            val numericValue = binding.appCompatEditText.getNumericValue()
            Log.d("DialPadActivity", "Confirm button clicked: $numericValue")
        }

        setupCustomKeyboard()
    }

    private fun setupButtonListener(button: AppCompatTextView, digit: String) {
        button.setOnClickListener {
            Log.d("DialPadActivity", "$digit clicked") // Use Log.d for debug
            viewModel.onDigitPressed(digit)
        }
    }

    private fun setupCustomKeyboard() {
        setupButtonListener(binding.btn1, "1")
        setupButtonListener(binding.btn2, "2")
        setupButtonListener(binding.btn3, "3")
        setupButtonListener(binding.btn4, "4")
        setupButtonListener(binding.btn5, "5")
        setupButtonListener(binding.btn6, "6")
        setupButtonListener(binding.btn7, "7")
        setupButtonListener(binding.btn8, "8")
        setupButtonListener(binding.btn9, "9")
        setupButtonListener(binding.btn0, "0")
        setupButtonListener(binding.btnDot, ".")

        binding.btnBackspace.setOnClickListener {
            Log.d("DialPadActivity", "Backspace clicked")
            viewModel.onBackspacePressed()
        }
    }
}
