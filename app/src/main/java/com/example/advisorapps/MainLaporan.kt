package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityLaporanBinding
import com.example.advisorapps.lapServis.LapAdapter
import com.example.advisorapps.lapServis.ResponseLap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainLaporan : AppCompatActivity(){

    private lateinit var list: ArrayList<ResponseLap>
    lateinit var binding: ActivityLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainLaporan, MainActivity::class.java))
        }

        binding.rvLaporan.setHasFixedSize(true)
        binding.rvLaporan.layoutManager = LinearLayoutManager(this)

        getDataLap()
    }

    fun getDataLap(){
        val api = RetrofitClient().getLap()
        api.getlap().enqueue(object : Callback<ArrayList<ResponseLap>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseLap>>,
                response: Response<ArrayList<ResponseLap>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onResponse: ", )

                response.body()?.let { list.addAll(it) }
                var adapter = LapAdapter(list)
                binding.rvLaporan.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseLap>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }
}