package com.example.advisorapps

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.databinding.ActivityUserBinding

class MainActivityProfileUser : AppCompatActivity() {

    private var binding : ActivityUserBinding? = null
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        binding?.tvUsername?.text = profil.getString("username", null)
        binding?.tvNmUser?.text = profil.getString("nm_user", null)
        binding?.tvRole?.text = if (profil.getString("role", null) == "1")"Foreman" else "Customer"

    }
}