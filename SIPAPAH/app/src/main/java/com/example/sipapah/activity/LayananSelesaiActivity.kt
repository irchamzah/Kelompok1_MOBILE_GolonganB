package com.example.sipapah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.adapter.AdapterLayananMenunggu
import com.example.sipapah.adapter.AdapterLayananSelesai
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

class LayananSelesaiActivity : AppCompatActivity() {

    lateinit var rvLayananSelesai: RecyclerView
    lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_selesai)

        setToolbar()

        rvLayananSelesai = findViewById(R.id.rv_layanan_selesai)

        sp = SharedPref(this)

        getLayananSelesai()
    }

    private var listLayananSelesai: ArrayList<Layanan> = ArrayList()

    fun getLayananSelesai() {
        pb_loading.visibility = View.VISIBLE

        if(sp.getUser() == null){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val id = sp.getUser()!!.id

        ApiConfig.instanceRetrofit.getlayananselesai(id).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    listLayananSelesai = respon.layanans
                    displayLayananSelesai()
                }

            }
        })
    }

    fun displayLayananSelesai() {
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvLayananSelesai.adapter = AdapterLayananSelesai(this,listLayananSelesai)
        rvLayananSelesai.layoutManager = layoutManager
        pb_loading.visibility = View.GONE

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