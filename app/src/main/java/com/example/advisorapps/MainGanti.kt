package com.example.advisorapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityMainGantiBinding
import com.example.advisorapps.infoServis.ResponseInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainGanti : AppCompatActivity() {

    lateinit var binding: ActivityMainGantiBinding

    private var id_servis :String?=""
    private var pgj :String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGantiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainGanti, MainPemberitahuan::class.java))
        }
        binding.btnGanti.setOnClickListener {
            id_servis = binding.tv1.text.toString()
            pgj = binding.tv2.text.toString()

            when {
                id_servis =="" ->{
                    binding.tv1.error ="Tidak boleh kosong"
                }
                pgj =="" ->{
                    binding.tv2.error ="Tidak boleh kosong"
                }
                else ->{
                    addData()
                }
            }
        }
    }

    fun addData(){
        val api = RetrofitClient().getInfo()
        api.addinfo(
            binding.tv1.text.toString(),
            binding.tv2.text.toString()
        ).enqueue(object : Callback<ResponseInfo>{
            override fun onResponse(call: Call<ResponseInfo>, response: Response<ResponseInfo>) {
                if (response.isSuccessful){
                    Toast.makeText(this@MainGanti, "Item ditambah",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainGanti, MainPemberitahuan::class.java))

                }else{
                    Toast.makeText(this@MainGanti, "Item gagal ditambah",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseInfo>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }
}