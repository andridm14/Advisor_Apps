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
import com.example.advisorapps.databinding.ActivityKendaraanBinding
import com.example.advisorapps.kendaraan.ResponseKendaraan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainKendaraan : AppCompatActivity() {

    lateinit var profil : SharedPreferences
    lateinit var binding: ActivityKendaraanBinding

    private var stnk    :String=""
    private var id_user :String=""
    private var model   :String=""
    private var warna   :String=""
    private var tahun   :String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKendaraanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //cek sessiion
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        binding.Back.setOnClickListener {
            startActivity(Intent(this@MainKendaraan, RegistrasiActivity::class.java))
        }

        binding.btnRegist.setOnClickListener {
            stnk    = binding.tvStnk.text.toString()
            id_user = binding.tvIdUser.text.toString()
            model   = binding.tvItemModel.text.toString()
            warna   = binding.tvItemWarna.text.toString()
            tahun   = binding.tvItemTahun.text.toString()

            when {
                stnk =="" ->{
                    binding.tvStnk.error ="Tidak boleh kosong"
                }
                id_user =="" ->{
                    binding.tvIdUser.error ="Tidak boleh kosong"
                }
                model =="" ->{
                    binding.tvItemModel.error ="Tidak boleh kosong"
                }
                warna =="" ->{
                    binding.tvItemWarna.error ="Tidak boleh kosong"
                }
                tahun =="" ->{
                    binding.tvItemTahun.error ="Tidak boleh kosong"
                }
                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    createkendaraan()
                }
            }
        }
        //list model
        val models = listOf(
            "Avanza", "Veloz", "Rush", "Calya", "Sienta", "Raize", "Agya", "Yaris", "Foetuner",
            "Innova", "Camry", "Corolla", "Alpard", "Vios", "Land Cruiser", "CHR", "BZ4X"
        )
        val adaptermod = ArrayAdapter(this, R.layout.list_model_kendaraan, models)
        binding.tvItemModel.setAdapter(adaptermod)
        //list warna
        val warnas = listOf(
            "White", "Black", "Grey", "Blue", "Red", "Yellow", "Green", "Brown", "Silver"
        )
        val adapterwar = ArrayAdapter(this, R.layout.list_item_warna, warnas)
        binding.tvItemWarna.setAdapter(adapterwar)
        //list tahun
        val tahuns = listOf(
            "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"
        )
        val adapter = ArrayAdapter(this, R.layout.list_tahun_kendaraan, tahuns)
        binding.tvItemTahun.setAdapter(adapter)
    }

    fun createkendaraan(){
        val api = RetrofitClient().createKendaraan()
        api.postKendaraan(
            binding.tvStnk.text.toString(),
            binding.tvIdUser.text.toString(),
            binding.tvItemModel.text.toString(),
            binding.tvItemWarna.text.toString(),
            binding.tvItemTahun.text.toString()
        ).enqueue(object : Callback<ResponseKendaraan>{
            override fun onResponse(
                call: Call<ResponseKendaraan>,
                response: Response<ResponseKendaraan>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@MainKendaraan, "Kendaraan berhasil ditambahkan",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainKendaraan, LoginActivity::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else{
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@MainKendaraan,
                        " kendaraan Gagal ditambahkan!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }
        })
    }
}