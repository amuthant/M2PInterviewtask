package com.example.m2pinterviewtask.dialpad

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m2pinterviewtask.R
import com.example.m2pinterviewtask.databinding.ActivityDialPadBinding

class DialPadActivity : AppCompatActivity() {

    private var binding = ActivityDialPadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =
        setContentView(R.layout.activity_dial_pad)



    }
}