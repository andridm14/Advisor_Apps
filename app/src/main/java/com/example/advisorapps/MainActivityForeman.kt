package com.example.advisorapps

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.databinding.ActivityMainForemanBinding

class MainActivityForeman : AppCompatActivity() {

    private lateinit var profil : SharedPreferences
    lateinit var binding: ActivityMainForemanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainForemanBinding.inflate(layoutInflater)
        profil = getSharedPreferences("login_session", MODE_PRIVATE)
        setContentView(binding.root)

        binding.ivservice.setOnClickListener {
            startActivity(Intent(this@MainActivityForeman, MainServiceForeman::class.java))
        }
        binding.ivPengecekan.setOnClickListener {
            startActivity(Intent(this@MainActivityForeman, MainItemForeman::class.java))
        }

        binding.ivLogout.setOnClickListener {
            profil.edit().clear().commit()
            startActivity(Intent(this@MainActivityForeman, LoginActivity::class.java))
            finish()
        }
    }
}