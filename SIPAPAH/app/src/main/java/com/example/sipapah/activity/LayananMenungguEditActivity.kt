package com.example.sipapah.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sipapah.R
import com.example.sipapah.model.Layanan
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kreasi.*
import kotlinx.android.synthetic.main.activity_layanan_edit.*
import kotlinx.android.synthetic.main.toolbar.*


class LayananMenungguEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_edit)

        getData()

        setToolbar()
    }

    private fun getData() {
        var data = intent.getStringExtra("dataLayananMenunggu")
        var layanan = Gson().fromJson<Layanan>(data, Layanan::class.java)

        // Set Value
        var foto = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/storage/"+layanan.file
        Picasso.get()
                .load(foto)
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .resize(500,500).centerInside()
                .into(img_layanan_edit_foto)
        var edtLayananEditKategori = findViewById<View>(R.id.edt_layanan_edit_kategori) as EditText
        edtLayananEditKategori.setText(layanan.category_id, TextView.BufferType.EDITABLE)
        tv_layanan_edit_tanggaljemput.text = layanan.tanggaljemput
        var edtLayananEditKeterangan = findViewById<View>(R.id.edt_layanan_edit_keterangan) as EditText
        edtLayananEditKeterangan.setText(layanan.keterangan, TextView.BufferType.EDITABLE)
    }

    fun setToolbar(){
        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Edit Pesanan" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}