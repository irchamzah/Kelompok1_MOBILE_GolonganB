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
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edt_email
import kotlinx.android.synthetic.main.activity_register.edt_password
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var sp:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sp = SharedPref(this)

        btn_register.setOnClickListener{
            register()
        }

        btn_google.setOnClickListener {
            dataDummy()
        }

        btn_punyaAkun.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    fun dataDummy(){
        edt_nama.setText("user")
        edt_email.setText("user@gmail.com")
        edt_password.setText("user123")
    }

    fun register(){

        if(edt_nama.text.isEmpty()){

            edt_nama.error = "Kolom Nama Tidak Boleh Kosong"
            edt_nama.requestFocus()
            return

        } else if(edt_email.text.isEmpty()) {

            edt_email.error = "Kolom Email Tidak Boleh Kosong"
            edt_email.requestFocus()
            return

        } else if(edt_nohp.text.isEmpty()) {

            edt_nohp.error = "Kolom Email Tidak Boleh Kosong"
            edt_nohp.requestFocus()
            return

        } else if(edt_alamat.text.isEmpty()) {

            edt_alamat.error = "Kolom Email Tidak Boleh Kosong"
            edt_alamat.requestFocus()
            return

        } else if(edt_password.text.isEmpty()) {

            edt_password.error = "Kolom Password Tidak Boleh Kosong"
            edt_password.requestFocus()
            return

        }

        pb_loading.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(), edt_email.text.toString(), edt_nohp.text.toString(),edt_alamat.text.toString(), edt_password.text.toString()).enqueue(object : Callback<ResponModel>{

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                pb_loading.visibility = View.GONE

                val respon = response.body()!!

                if (respon.success == 1){
                    sp.setStatusLogin(true)

                    sp.setUser(respon.user)

                    val intent =Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Berhasil Mendaftar. Selamat Datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@RegisterActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb_loading.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })


    }

}