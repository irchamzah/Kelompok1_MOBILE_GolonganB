package com.example.sipapah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.adapter.AdapterLayananDitolak
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.ResponModel
import kotlinx.android.synthetic.main.activity_pesan.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class LayananDitolakActivity : AppCompatActivity() {

    lateinit var rvLayananDitolak: RecyclerView
    lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_ditolak)

        setToolbar()

        rvLayananDitolak = findViewById(R.id.rv_layanan_ditolak)

        sp = SharedPref(this)

        getLayananDitolak()
    }

    private var listLayananDitolak: ArrayList<Layanan> = ArrayList()

    fun getLayananDitolak() {
        pb_loading.visibility = View.VISIBLE

        if(sp.getUser() == null){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val id = sp.getUser()!!.id

        ApiConfig.instanceRetrofit.getlayananditolak(id).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    listLayananDitolak = respon.layanans
                    displayLayananDitolak()
                }

            }
        })
    }

    fun displayLayananDitolak() {
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvLayananDitolak.adapter = AdapterLayananDitolak(this,listLayananDitolak)
        rvLayananDitolak.layoutManager = layoutManager
        pb_loading.visibility = View.GONE

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