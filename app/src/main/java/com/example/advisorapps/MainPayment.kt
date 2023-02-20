package com.example.advisorapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.advisorapps.databinding.ActivityMainPaymentBinding

class MainPayment : AppCompatActivity() {

    lateinit var binding: ActivityMainPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}