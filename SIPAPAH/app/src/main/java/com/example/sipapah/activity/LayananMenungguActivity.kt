package com.example.sipapah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.adapter.AdapterLayananMenunggu
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.ResponModel
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
//R
class LayananMenungguActivity : AppCompatActivity() {

    lateinit var rvLayananMenunggu: RecyclerView
    lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_menunggu)
        setToolbar()

        rvLayananMenunggu = findViewById(R.id.rv_layanan_menunggu)

        sp = SharedPref(this)

        getLayananMenunggu()


    }

    private var listLayananMenunggu: ArrayList<Layanan> = ArrayList()

    fun getLayananMenunggu() {

        if(sp.getUser() == null){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val id = sp.getUser()!!.id

        ApiConfig.instanceRetrofit.getlayanan(id).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    listLayananMenunggu = respon.layanans
                    displayLayananMenunggu()
                }

            }
        })
    }

    fun displayLayananMenunggu() {




        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvLayananMenunggu.adapter = AdapterLayananMenunggu(this,listLayananMenunggu)
        rvLayananMenunggu.layoutManager = layoutManager

    }

    fun setToolbar(){
        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Pesanan Menunggu" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}