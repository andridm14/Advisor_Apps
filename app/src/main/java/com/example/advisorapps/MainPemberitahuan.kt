package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityPemberitahuanBinding
import com.example.advisorapps.pemServis.PemAdapter
import com.example.advisorapps.pemServis.ResponsePem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPemberitahuan : AppCompatActivity() {

    private lateinit var list: ArrayList<ResponsePem>
    lateinit var binding: ActivityPemberitahuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemberitahuanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.back.setOnClickListener {
            startActivity(Intent(this@MainPemberitahuan, MainActivity::class.java))
        }

        binding.rvPemberitahuan.setHasFixedSize(true)
        binding.rvPemberitahuan.layoutManager = LinearLayoutManager(this)

        getDataPem()
    }

    fun getDataPem(){
        val api = RetrofitClient().getPem()
        api.getpem().enqueue(object : Callback<ArrayList<ResponsePem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponsePem>>,
                response: Response<ArrayList<ResponsePem>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onResponse:" )

                response.body()?.let { list.addAll(it) }
                var adapter = PemAdapter(list){
                    Intent(this@MainPemberitahuan, MainGanti::class.java).apply {
                        startActivity(this)
                    }
                }
                binding.rvPemberitahuan.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponsePem>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }

}