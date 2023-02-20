package com.example.advisorapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityRegistrasiBinding
import com.example.advisorapps.regist.ResponseRegist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrasiActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrasiBinding

    private var nm_user :String=""
    private var username :String=""
    private var password :String=""
    private var role :String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAkunLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
        binding.btnRegist.setOnClickListener {
            startActivity(Intent(applicationContext, MainKendaraan::class.java))
        }

        val roles = listOf("2")
        val adapter = ArrayAdapter(this, R.layout.list_item_role, roles)
        binding.tvItemRole.setAdapter(adapter)

        binding.btnRegist.setOnClickListener {
            nm_user = binding.tvname.text.toString()
            username = binding.tvUsername.text.toString()
            password = binding.tvPass.text.toString()
            role = binding.tvItemRole.text.toString()

            when{
                nm_user =="" ->{
                    binding.tvname.error ="Tidak boleh kosong"
                }
                username == "" ->{
                    binding.tvUsername.error ="Tidak boleh kosong"
                }
                password == "" ->{
                    binding.tvPass.error ="Tidak boleh kosong"
                }
                role == "" ->{
                    binding.tvItemRole.error ="Tidak boleh kosong"
                }
                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    createRegist()
                }
            }
        }
    }

    fun createRegist(){
        val api = RetrofitClient().register()
        api.regist(
            binding.tvname.text.toString(),
            binding.tvUsername.text.toString(),
            binding.tvPass.text.toString(),
            binding.tvItemRole.text.toString()
        ).enqueue(object : Callback<ResponseRegist>{
            override fun onResponse(
                call: Call<ResponseRegist>,
                response: Response<ResponseRegist>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@RegistrasiActivity, "Registrasi berhasil",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@RegistrasiActivity,
                        MainKendaraan::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@RegistrasiActivity,
                        "Registrasi Gagal!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseRegist>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }
        })
    }
}