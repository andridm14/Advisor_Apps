package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivitySaranForemanBinding
import com.example.advisorapps.foremanSaran.ResponseSaran
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainSaranForeman : AppCompatActivity() {

    lateinit var binding: ActivitySaranForemanBinding

    private var id_servis :String=""
    private var stnk :String=""
    private var saran :String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaranForemanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainSaranForeman, MainActivityForeman::class.java))
        }

        binding.btnSaran.setOnClickListener {
            id_servis = binding.tvIdService.text.toString()
            stnk = binding.tvStnk.text.toString()
            saran = binding.tvSaran.text.toString()

            when{
                id_servis =="" ->{
                    binding.tvIdService.error ="Tidak boleh kosong"
                }
                stnk =="" ->{
                    binding.tvStnk.error ="Tidak boleh kosong"
                }
                saran =="" ->{
                    binding.tvSaran.error ="Tidak boleh kosong"
                }
                else ->{
                    binding.progress.visibility = View.VISIBLE
                    dataSaran()
                }
            }
        }
    }

    fun dataSaran(){
        val api = RetrofitClient().createSaran()
        api.postSaran(
            binding.tvIdService.text.toString(),
            binding.tvStnk.text.toString(),
            binding.tvSaran.text.toString()
        ).enqueue(object : Callback<ResponseSaran>{
            override fun onResponse(call: Call<ResponseSaran>, response: Response<ResponseSaran>) {
                if (response.isSuccessful){
                    binding.progress.visibility = View.VISIBLE
                    Toast.makeText(this@MainSaranForeman, "Saran berhasil diberikan",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainSaranForeman, MainSaranForeman::class.java))
                }else {
                    Toast.makeText(this@MainSaranForeman, "Saran gagal diberikan",
                        Toast.LENGTH_LONG).show()
                    binding.progress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseSaran>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }
}