package com.example.advisorapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.advisorapps.databinding.ActivityItemUpdateBinding

class MainUpItemForeman : AppCompatActivity() {

    lateinit var binding: ActivityItemUpdateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainUpItemForeman, MainItemForeman::class.java))
        }

        val kets = listOf(
            "Proses", "Selesai"
        )
        val adapter = ArrayAdapter(this, R.layout.list_item_up, kets)
        binding.tvItemKet.setAdapter(adapter)

        binding.btnUpdate.setOnClickListener {
            startActivity(Intent(this@MainUpItemForeman, MainItemForeman::class.java))
        }
    }
}