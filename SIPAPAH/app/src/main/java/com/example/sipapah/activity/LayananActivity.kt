package com.example.sipapah.activity

import android.app.DatePickerDialog
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
import kotlinx.android.synthetic.main.activity_pesan.*
import kotlinx.android.synthetic.main.fragment_layanan.*
import kotlinx.android.synthetic.main.fragment_layanan.btn_pesanan
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LayananActivity : AppCompatActivity() {

    lateinit var sp:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan)

        sp = SharedPref(this)

        btn_buatpesanan.setOnClickListener{
            buatpesanan()
        }

        //Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //button click to show DatePickerDialog
        btn_tanggalJemput.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                //set to text view
                val mmMonth = mMonth+1
                tv_tanggaljemput.setText(""+mYear+"-"+mmMonth+"-"+mDay)
            }, year, month, day)
            //show dialog
            dpd.show()
        }

        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Buat Pesanan" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    fun buatpesanan(){

        if(edt_kategori.text.isEmpty()){

            edt_kategori.error = "Kolom Kategori Tidak Boleh Kosong"
            edt_kategori.requestFocus()
            return

        } else if(edt_upfoto.text.isEmpty()) {

            edt_upfoto.error = "Kolom Foto Tidak Boleh Kosong"
            edt_upfoto.requestFocus()
            return

        } else if(tv_tanggaljemput.text.isEmpty()) {

            tv_tanggaljemput.error = "Kolom tanggal Tidak Boleh Kosong"
            tv_tanggaljemput.requestFocus()

        } else if(edt_keterangan.text.isEmpty()) {

            edt_keterangan.error = "Kolom keterangan Tidak Boleh Kosong"
            edt_keterangan.requestFocus()
            return

        }

        pb_loading.visibility = View.VISIBLE

        val id = SharedPref(this).getUser()!!.id


        ApiConfig.instanceRetrofit.setmemesan(id,edt_kategori.text.toString(), edt_upfoto.text.toString(), tv_tanggaljemput.text.toString(), edt_keterangan.text.toString()).enqueue(object : Callback<ResponModel>{

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                pb_loading.visibility = View.GONE

                val respon = response.body()!!

                if (respon.success == 1){
                    sp.setPesanan(respon.layanan)
                    Toast.makeText(this@LayananActivity, "Berhasil Memesan ", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@LayananActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb_loading.visibility = View.GONE
                Toast.makeText(this@LayananActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })


    }

}