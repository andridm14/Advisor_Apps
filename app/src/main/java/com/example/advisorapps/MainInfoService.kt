package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityInfoServiceBinding
import com.example.advisorapps.infoServis.InfoAdapter
import com.example.advisorapps.infoServis.ResponseInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInfoService : AppCompatActivity(){

    private lateinit var list: ArrayList<ResponseInfo>
    lateinit var binding: ActivityInfoServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainInfoService, MainActivity::class.java))
        }

        binding.rvInfo.setHasFixedSize(true)
        binding.rvInfo.layoutManager = LinearLayoutManager(this)

        getDataInfo()
    }

    fun getDataInfo(){
        val api = RetrofitClient().getInfo()
        api.getinfo().enqueue(object : Callback<ArrayList<ResponseInfo>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseInfo>>,
                response: Response<ArrayList<ResponseInfo>>
            ) {
                val responseCode:String = response.code().toString()
                Log.i(responseCode, "onResponse: ", )

                response.body()?.let { list.addAll(it) }
                var adapter = InfoAdapter(list)
                binding.rvInfo.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseInfo>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }

}