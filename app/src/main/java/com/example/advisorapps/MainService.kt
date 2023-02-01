package com.example.advisorapps

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.databinding.ActivityServiceBinding

class MainService : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //list jenis service
        val items = listOf("Paket 1", "Paket 2", "Paket 3", "Paket 4")
        val adapter = ArrayAdapter(this, R.layout.list_jenis_service, items)
        binding.dropJenisServis.setAdapter(adapter)

        //cek sessiion
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

    }
}