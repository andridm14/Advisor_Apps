package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.dafServis.ResponseServis
import com.example.advisorapps.dafServis.ServisAdapter
import com.example.advisorapps.databinding.ActivityServiceForemanBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainServiceForeman: AppCompatActivity() {

    private lateinit var list: ArrayList<ResponseServis>
    lateinit var binding: ActivityServiceForemanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityServiceForemanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainServiceForeman,
                MainActivityForeman::class.java))
        }

        list = ArrayList()
        binding.rvDataServis.setHasFixedSize(true)
        binding.rvDataServis.layoutManager = LinearLayoutManager(this)

        getDataServis()
    }

    fun getDataServis(){
        val api = RetrofitClient().createServis()
        api.getservis().enqueue(object : Callback<ArrayList<ResponseServis>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseServis>>,
                response: Response<ArrayList<ResponseServis>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onResponse: ", )

                response.body()?.let { list.addAll(it) }
                var adapter = ServisAdapter(list){
                    Intent(this@MainServiceForeman, MainSaranForeman::class.java).apply {
                        startActivity(this)
                    }
                }
                binding.rvDataServis.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseServis>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }
}