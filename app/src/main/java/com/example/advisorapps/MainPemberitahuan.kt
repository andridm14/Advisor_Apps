package com.example.advisorapps

import android.util.Log
import android.view.ActionMode
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

    override fun onActionModeStarted(mode: ActionMode?) {
        super.onActionModeStarted(mode)
        binding = ActivityPemberitahuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()

        binding.rvPemberitahuan.setHasFixedSize(true)
        binding.rvPemberitahuan.layoutManager = LinearLayoutManager(this)

        getDataPem()
    }

    private fun getDataPem(){
        val api = RetrofitClient().getPem()
        api.getpem().enqueue(object : Callback<ArrayList<ResponsePem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponsePem>>,
                response: Response<ArrayList<ResponsePem>>
            ) {
                val responseCode: String = response.code().toString()
                Log.e(responseCode, "onResponse: ", )

                response.body()?.let { list.addAll(it) }
                var adapter = PemAdapter(list)
                binding.rvPemberitahuan.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponsePem>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }
}