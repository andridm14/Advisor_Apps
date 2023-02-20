package com.example.advisorapps

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.dafServis.ResponseServis
import com.example.advisorapps.databinding.ActivityServiceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainService : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    lateinit var profil : SharedPreferences

    private var id_user: String =""
    private var stnk: String =""
    private var model: String =""
    private var jenis_servis: String =""
    private var keluhan: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainService, MainActivity::class.java))
        }

        binding.btnDaftarService.setOnClickListener {
            id_user = binding.tvidUser.text.toString()
            stnk = binding.tvStnk.text.toString()
            model = binding.tvModel.text.toString()
            jenis_servis = binding.tvDropitem.text.toString()
            keluhan = binding.tvKeluhan.text.toString()

            when{
                id_user == "" -> {
                    binding.tvidUser.error = "Tidak boleh kosong"
                }
                stnk == "" -> {
                    binding.tvStnk.error = "Tidak boleh kosong"
                }
                model == "" -> {
                    binding.tvModel.error = "Tidak boleh kosong"
                }
                jenis_servis == "" -> {
                    binding.tvDropitem.error = "Tidak boleh kosong"
                }
                keluhan == "" -> {
                    binding.tvKeluhan.error = "Tidak boleh kosong"
                }
                else -> {
                    binding.progressBar.visibility = View.VISIBLE
                    createData()
                }
            }
        }

        //list jenis service
        val items = listOf("Paket 1", "Paket 2", "Paket 3", "Paket 4")
        val adapter = ArrayAdapter(this, R.layout.list_jenis_service, items)
        binding.tvDropitem.setAdapter(adapter)

        //cek sessiion
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

    }

    fun createData(){
        val api = RetrofitClient().createServis()
        api.createservis(
            binding.tvidUser.text.toString(),
            binding.tvStnk.text.toString(),
            binding.tvModel.text.toString(),
            binding.tvDropitem.text.toString(),
            binding.tvKeluhan.text.toString()
        ).enqueue(object : Callback<ResponseServis>{
            override fun onResponse(
                call: Call<ResponseServis>,
                response: Response<ResponseServis>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@MainService, "Daftar Service Berhasil",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainService,
                        MainActivity::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else{
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@MainService,
                        "Daftar Service Gagal!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseServis>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }
}