package com.example.advisorapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.advisorapps.api.RetrofitClient
import com.example.advisorapps.databinding.ActivityLoginBinding
import com.example.advisorapps.login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null
    private var user : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnLogin.setOnClickListener {
            user = binding!!.textUsername.text.toString()
            pass = binding!!.textPassword.text.toString()

            when {
                user == "" -> {
                    binding!!.textUsername.error = "Username tidak boleh kosong"
                }
                pass == "" -> {
                    binding!!.textPassword.error = "Password tidak boleh kosong"
                }
                else -> {
                    binding!!.progressLogin.visibility = View.VISIBLE
                    getData()
                }
            }
        }
    }

    private fun getData() {
        val api = RetrofitClient().getInstance()
        api.login(user, pass).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful){
                    if (response.body()?.response == true){
                        if (response.body()?.payload?.role == "1"){
                            binding!!.progressLogin.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity,
                                MainActivityForeman::class.java))
                            finish()
                        }else {
                            binding!!.progressLogin.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity,
                                MainActivity::class.java))
                            finish()
                        }
                    }else {
                        binding!!.progressLogin.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Login gagal! Periksa Kembali username & password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else {
                    Toast.makeText(this@LoginActivity,
                        "Login gagal terjadi kesalahan",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }
        })
    }
}