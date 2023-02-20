package com.example.advisorapps

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.databinding.ActivityUserBinding

class MainActivityProfileUser : AppCompatActivity() {

    lateinit var binding : ActivityUserBinding
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        binding.tvId.text = profil.getString("id_user", null)
        binding.tvUsername.text = profil.getString("username", null)
        binding.tvNmUser.text = profil.getString("nm_user", null)
        binding.tvRole.text = if (profil.getString("role", null) == "1")"Foreman" else "Customer"

        binding.btnKdr.setOnClickListener {
            startActivity(Intent(this@MainActivityProfileUser, MainKendaraan::class.java))
        }
    }
}