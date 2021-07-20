package com.example.sipapah.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipapah.R
import kotlinx.android.synthetic.main.toolbar.*

class LayananSelesaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_selesai)

        setToolbar()
    }

    fun setToolbar(){
        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Pesanan Selesai" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}