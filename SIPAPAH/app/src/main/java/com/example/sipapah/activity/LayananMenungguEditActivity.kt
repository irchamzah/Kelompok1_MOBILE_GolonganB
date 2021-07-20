package com.example.sipapah.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sipapah.R
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.layananActivity.LayananMenungguEditViewModel
import com.example.sipapah.model.Layanan
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kreasi.*
import kotlinx.android.synthetic.main.activity_layanan_edit.*
import kotlinx.android.synthetic.main.activity_layanan_edit.pb_loading
import kotlinx.android.synthetic.main.activity_pesan.*
import kotlinx.android.synthetic.main.toolbar.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.util.*


class LayananMenungguEditActivity : AppCompatActivity() {

    lateinit var vm : LayananMenungguEditViewModel
    lateinit var sp: SharedPref
    lateinit var btnFoto: Button
    lateinit var imgFoto: ImageView

    private val viewModel: LayananMenungguEditActivity by viewModels()

    var layanan_detail_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_edit)

        btnFoto = findViewById(R.id.btn_layanan_edit_foto)
        imgFoto = findViewById(R.id.img_layanan_edit_foto)
        sp = SharedPref(this)
        vm = ViewModelProvider(this).get(LayananMenungguEditViewModel::class.java)

        getData()

        mainbutton()
        obeservers()
        setToolbar()
    }

    fun mainbutton(){
        dialogpilihtanggal()
        btnFoto.setOnClickListener{ EasyImage.openGallery(this, 1) }
        btn_layanan_edit_buatpesanan.setOnClickListener{ simpan() }
    }

    private fun simpan(){
        if(edt_layanan_edit_kategori.text.isEmpty()){
            edt_layanan_edit_kategori.error = "Kolom Kategori Tidak Boleh Kosong"
            edt_layanan_edit_kategori.requestFocus()
            return
        }else if(tv_layanan_edit_tanggaljemput.text.isEmpty()) {
            tv_layanan_edit_tanggaljemput.error = "Kolom tanggal Tidak Boleh Kosong"
            tv_layanan_edit_tanggaljemput.requestFocus()
            return
        } else if(edt_layanan_edit_keterangan.text.isEmpty()) {
            edt_layanan_edit_keterangan.error = "Kolom keterangan Tidak Boleh Kosong"
            edt_layanan_edit_keterangan.requestFocus()
            return
        }
        pb_loading.visibility = View.VISIBLE

//        val id_user = sp.getUser()!!.id
        val layanan = Layanan()
        layanan.id = layanan_detail_id
        layanan.category_id = edt_layanan_edit_kategori.text.toString()
//        layanan.user_id = id_user
        layanan.tanggaljemput = tv_layanan_edit_tanggaljemput.text.toString()
        layanan.keterangan = edt_layanan_edit_keterangan.text.toString()
        if(fileImage == null){
            Toast.makeText(this, "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            return
        }
        vm.update(layanan, fileImage!!)
    }

    var fileImage: File? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object: DefaultCallback() {
            override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {

                fileImage =  imageFile
                Picasso.get()
                        .load(imageFile!!).resize(500,500)
                        .centerInside()
                        .placeholder(R.drawable.sipapa_hijau)
                        .error(R.drawable.sipapa_hijau)
                        .into(imgFoto)
            }
        })
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

        layanan_detail_id = layanan.id
    }

    fun dialogpilihtanggal(){
        //Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //button click to show DatePickerDialog
        btn_layanan_edit_tanggalJemput.setOnClickListener{
            val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                        //set to text view
                        val mmMonth = mMonth + 1
                        btn_layanan_edit_tanggalJemput.setText("" + mYear + "-" + mmMonth + "-" + mDay)
                    },
                    year,
                    month,
                    day
            )
            //show dialog
            dpd.show()
        }
    }

    fun obeservers(){
        vm.mData.observe(this, Observer {
            toast(""+it.message)
            val intent = Intent(this@LayananMenungguEditActivity, LayananMenungguActivity::class.java)
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
        supportActionBar!!.title = "Edit Pesanan" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}