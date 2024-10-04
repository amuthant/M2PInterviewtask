package com.example.m2pinterviewtask.dialpad

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.m2pinterviewtask.R
import com.example.m2pinterviewtask.databinding.ActivityDialPadBinding

class DialPadActivity : AppCompatActivity() {

    private lateinit var viewModel: DialPadViewModel
    private lateinit var binding: ActivityDialPadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDialPadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DialPadViewModel::class.java)







    }
}