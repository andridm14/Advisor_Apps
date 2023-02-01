package com.example.advisorapps

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.advisorapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        //menampilkan data
        binding?.tvName?.text = profil.getString("nm_user", null)

        //btn logout
        binding?.icExit?.setOnClickListener {
            //hapus session
            profil.edit().clear().commit()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        //btn account
        binding?.icAccount?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivityProfileUser::class.java))
        }

        //btn service
        binding?.imgService?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainService::class.java))
        }

        //btn infoservice
        binding?.imgMonitoring?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainInfoService::class.java))
        }

        //btn pemberitahuan
        binding?.imgPemberitahuan?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainPemberitahuan::class.java))
        }

        //btn lapServis
        binding?.imgReport?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainLaporan::class.java))
        }
    }
}