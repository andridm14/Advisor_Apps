package com.example.advisorapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityItemForemanBinding
import com.example.advisorapps.infoServis.ItemAdapter
import com.example.advisorapps.infoServis.ResponseInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainItemForeman : AppCompatActivity() {

    private lateinit var list: ArrayList<ResponseInfo>

    lateinit var binding : ActivityItemForemanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemForemanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = ArrayList()

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainItemForeman, MainActivityForeman::class.java))
        }

        binding.rvItem.setHasFixedSize(true)
        binding.rvItem.layoutManager = LinearLayoutManager(this)

        getDataItem()
    }

    fun getDataItem(){
        val api = RetrofitClient().getInfo()
        api.getinfo().enqueue(object : Callback<ArrayList<ResponseInfo>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseInfo>>,
                response: Response<ArrayList<ResponseInfo>>
            ) {
                val responseCode:String = response.code().toString()
                Log.i(responseCode, "onResponse: ", )

                response.body()?.let { list.addAll(it) }
                var adapter = ItemAdapter(list){
                    Intent(this@MainItemForeman, MainUpItemForeman::class.java).apply {
                        startActivity(this)
                    }
                }
                binding.rvItem.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseInfo>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}