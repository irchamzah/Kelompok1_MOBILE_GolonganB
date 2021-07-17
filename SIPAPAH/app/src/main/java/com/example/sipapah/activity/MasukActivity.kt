package com.example.sipapah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipapah.R
import com.example.sipapah.helper.SharedPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_masuk.*
import kotlinx.android.synthetic.main.activity_masuk.btn_login

class MasukActivity : AppCompatActivity() {

    lateinit var sp:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        sp = SharedPref(this)

        mainButton()
    }

    private fun mainButton(){
        btn_login.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

}