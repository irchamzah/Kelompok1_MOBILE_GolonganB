package com.example.sipapah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var sp:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sp = SharedPref(this)

        btn_login.setOnClickListener{
            login()
        }

        btn_buatAkun.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    fun login(){

        if(edt_email.text.isEmpty()) {

            edt_email.error = "Kolom Email Tidak Boleh Kosong"
            edt_email.requestFocus()
            return

        } else if(edt_password.text.isEmpty()) {

            edt_password.error = "Kolom Password Tidak Boleh Kosong"
            edt_password.requestFocus()
            return

        }

        pb_loading.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(edt_email.text.toString(), edt_password.text.toString()).enqueue(object : Callback<ResponModel> {

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                pb_loading.visibility = View.GONE

                val respon = response.body()!!

                if (respon.success == 1){

                    sp.setStatusLogin(true)

                    sp.setUser(respon.user)
//                    sp.setString(sp.name, respon.user.name)
//                    sp.setString(sp.email, respon.user.email)
//                    sp.setString(sp.foto, respon.user.foto)
//                    sp.setString(sp.alamat, respon.user.alamat)
//                    sp.setString(sp.nohp, respon.user.nohp)

                    val intent =Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Berhasil Login. Selamat Datang "+respon.user.name, Toast.LENGTH_SHORT).show()

                } else{
                    Toast.makeText(this@LoginActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

                pb_loading.visibility = View.GONE

                Toast.makeText(this@LoginActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })


    }
}