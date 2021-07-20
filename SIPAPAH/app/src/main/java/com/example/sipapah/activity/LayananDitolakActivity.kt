package com.example.sipapah.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipapah.R
import kotlinx.android.synthetic.main.toolbar.*

class LayananDitolakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_ditolak)

        setToolbar()
    }

    fun setToolbar(){
        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Pesanan Ditolak" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}