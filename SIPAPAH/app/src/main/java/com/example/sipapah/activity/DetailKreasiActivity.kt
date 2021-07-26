package com.example.sipapah.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipapah.R
import com.example.sipapah.model.Kreasi
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kreasi.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailKreasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kreasi)

        getData()
    }

    private fun getData() {
        val data = intent.getStringExtra("dataKreasi")
        val kreasi = Gson().fromJson<Kreasi>(data, Kreasi::class.java)

        // Set Value
        tv_judul.text = kreasi.nama
        val foto = "https://wsjti.id/sipapah/public/img/fotokreasi/"+kreasi.foto
        Picasso.get()
            .load(foto).resize(500,500).centerInside()
            .placeholder(R.drawable.sipapa_hijau)
            .error(R.drawable.sipapa_hijau)
            .into(image)
        tv_keterangan.text = kreasi.keterangan
        tv_keteranganDetail.text = kreasi.keterangan_detail

        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = kreasi.nama // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}