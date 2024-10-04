package com.example.m2pinterviewtask.dialpad

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.example.m2pinterviewtask.databinding.ActivityDialPadBinding
import com.example.m2pinterviewtask.DialPadViewModel
import com.example.m2pinterviewtask.DialPadViewModelFactory

class DialPadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialPadBinding
    private lateinit var viewModel: DialPadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDialPadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DialPadViewModelFactory()).get(DialPadViewModel::class.java)

        viewModel.currentInput.observe(this) { input ->
            binding.appCompatEditText.setText(input)
        }

        // Set up TextWatcher to enable/disable the Confirm button
        binding.appCompatEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val numericValue = binding.appCompatEditText.text.toString()

                Log.e("numericValue",numericValue.toString())
                //binding.btnConfirm.isEnabled = numericValue > 0  // Enable if value is greater than 0
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnConfirm.setOnClickListener {
            Toast.makeText(this, "Confirm?", Toast.LENGTH_SHORT).show() // Show confirmation Toast
        }

        setupCustomKeyboard()
    }


    private fun setupButtonListener(button: AppCompatTextView, digit: String) {
        button.setOnClickListener {
            Log.d("DialPadActivity", "$digit clicked")
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

        binding.closeBtn.setOnClickListener {
            Log.d("YourActivity", "Close button clicked")
            finish()
        }
    }
}
