package com.example.sipapah.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.layananActivity.LayananViewModel
import com.example.sipapah.model.Layanan
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pesan.*
import kotlinx.android.synthetic.main.fragment_layanan.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.RequestBody
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*

//R

class LayananActivity : AppCompatActivity() {

    lateinit var vm : LayananViewModel
    lateinit var sp:SharedPref
    lateinit var btnFoto:Button
    lateinit var imgFoto:ImageView

    private val viewModel: LayananActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan)

        btnFoto = findViewById(R.id.btn_foto)
        imgFoto = findViewById(R.id.img_foto
        )
        sp = SharedPref(this)
        vm = ViewModelProvider(this).get(LayananViewModel::class.java)

        mainbutton()
        obeservers()
        setToolbar()


    }

    fun obeservers(){
        vm.mData.observe(this, Observer {
            toast(""+it.message)
            val intent =Intent(this@LayananActivity, LayananMenungguActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            pb_loading.visibility = View.GONE

        })

        vm.onFailure.observe(this, Observer {
            pb_loading.visibility = View.GONE
            toast("error: $it")
        })

    }

    private fun toast(string: String){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    fun setToolbar(){
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


    fun mainbutton(){

        dialogpilihtanggal()

        btn_foto.setOnClickListener{
            EasyImage.openGallery(this, 1)
        }
        btn_buatpesanan.setOnClickListener{
//            buatpesanan()
            simpan()
        }

    }

    private fun simpan(){


        if(edt_kategori.text.isEmpty()){

            edt_kategori.error = "Kolom Kategori Tidak Boleh Kosong"
            edt_kategori.requestFocus()
            return

        }else if(tv_tanggaljemput.text.isEmpty()) {

            tv_tanggaljemput.error = "Kolom tanggal Tidak Boleh Kosong"
            tv_tanggaljemput.requestFocus()

        } else if(edt_keterangan.text.isEmpty()) {

            edt_keterangan.error = "Kolom keterangan Tidak Boleh Kosong"
            edt_keterangan.requestFocus()
            return

        }

        pb_loading.visibility = View.VISIBLE


        val id = sp.getUser()!!.id

        val layanan = Layanan()
        layanan.id = id
        layanan.category_id = edt_kategori.text.toString()
        layanan.tanggaljemput = tv_tanggaljemput.text.toString()
        layanan.keterangan = edt_keterangan.text.toString()
        if(fileImage == null){
            Toast.makeText(this, "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            return
        }
        vm.create(layanan, fileImage!!)



    }


    var fileImage: File? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object: DefaultCallback() {
            override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {

                fileImage =  imageFile
                Picasso.get()
                    .load(imageFile!!).resize(500,500).centerInside()
                    .placeholder(R.drawable.sipapa_hijau)
                    .error(R.drawable.sipapa_hijau)
                    .into(img_foto)
            }
        })



    }

    fun dialogpilihtanggal(){
        //Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //button click to show DatePickerDialog
        btn_tanggalJemput.setOnClickListener{
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    //set to text view
                    val mmMonth = mMonth + 1
                    tv_tanggaljemput.setText("" + mYear + "-" + mmMonth + "-" + mDay)
                },
                year,
                month,
                day
            )
            //show dialog
            dpd.show()
        }

    }




//    fun buatpesanan(){
//
//        if(edt_kategori.text.isEmpty()){
//
//            edt_kategori.error = "Kolom Kategori Tidak Boleh Kosong"
//            edt_kategori.requestFocus()
//            return
//
//        } else if(edt_upfoto.text.isEmpty()) {
//
//            edt_upfoto.error = "Kolom Foto Tidak Boleh Kosong"
//            edt_upfoto.requestFocus()
//            return
//
//        } else if(tv_tanggaljemput.text.isEmpty()) {
//
//            tv_tanggaljemput.error = "Kolom tanggal Tidak Boleh Kosong"
//            tv_tanggaljemput.requestFocus()
//
//        } else if(edt_keterangan.text.isEmpty()) {
//
//            edt_keterangan.error = "Kolom keterangan Tidak Boleh Kosong"
//            edt_keterangan.requestFocus()
//            return
//
//        }
//
//        pb_loading.visibility = View.VISIBLE
//
//        val id = sp.getUser()!!.id
//        val layanan_id = ""
//        val user_id = ""
//        val status_id = ""
//        val pendapatan = ""
//        val file = fileImage
//        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//        val path = MultipartBody.Part.createFormData("newimage", file!!.getName(), requestBody)
//
//        ApiConfig.instanceRetrofit.setmemesan(
//                id,
//                layanan_id,
//                edt_kategori.text.toString(),
//                user_id,
//                tv_tanggaljemput.text.toString(),
//                edt_keterangan.text.toString(),
//                status_id,
//                pendapatan,
//                path
//        ).enqueue(object : Callback<ResponModel> {
//
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//
//                pb_loading.visibility = View.GONE
//
//                val respon = response.body()!!
//
//                if (respon.success == 1) {
//                    sp.setPesanan(respon.layanan)
//                    Toast.makeText(this@LayananActivity, "Berhasil Memesan ", Toast.LENGTH_SHORT)
//                        .show()
//                } else {
//                    Toast.makeText(
//                        this@LayananActivity,
//                        "Error: " + respon.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                pb_loading.visibility = View.GONE
//                Toast.makeText(this@LayananActivity, "Error: " + t.message, Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//        })
//
//
//    }



//    private fun showLoading(){
//        alertDialog.show()
//        alertDialog.window!!.setLayout(650, 400)
//    }
//
//    private fun dismissLoading(){
//        alertDialog.dismiss()
//    }



}