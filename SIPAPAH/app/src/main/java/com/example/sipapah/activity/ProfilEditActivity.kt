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
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.fragment.ProfilFragment
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.layananActivity.ProfilEditViewModel
import com.example.sipapah.model.User
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kreasi.*
import kotlinx.android.synthetic.main.activity_profil_edit.*
import kotlinx.android.synthetic.main.toolbar.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.util.*


class ProfilEditActivity : AppCompatActivity() {

    lateinit var vm : ProfilEditViewModel
    lateinit var sp: SharedPref

    lateinit var imgFoto: ImageView
    lateinit var btnFoto: Button
    lateinit var edtNama: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtAlamat: EditText
    lateinit var edtNohp: EditText
    lateinit var btnPerbarui: Button

    private val viewModel: ProfilEditActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_edit)

        imgFoto = findViewById(R.id.edt_profil_edit_foto)
        btnFoto = findViewById(R.id.btn_profil_edit_foto)
        edtNama = findViewById(R.id.edt_profil_edit_nama)
        edtEmail = findViewById(R.id.edt_profil_edit_email)
        edtPassword = findViewById(R.id.edt_profil_edit_password)
        edtAlamat = findViewById(R.id.edt_profil_edit_alamat)
        edtNohp = findViewById(R.id.edt_profil_edit_nohp)
        btnPerbarui = findViewById(R.id.btn_profil_edit_perbarui)

        sp = SharedPref(this)
        vm = ViewModelProvider(this).get(ProfilEditViewModel::class.java)

        getData()

        mainbutton()
        obeservers()
        setToolbar()
    }

    fun mainbutton(){
        btnFoto.setOnClickListener{ EasyImage.openGallery(this, 1) }
        btnPerbarui.setOnClickListener{ perbarui() }
    }

    private fun perbarui(){
        if(edtNama.text.isEmpty()){
            edtNama.error = "Kolom Nama Tidak Boleh Kosong"
            edtNama.requestFocus()
            return
        }else if(edtEmail.text.isEmpty()) {
            edtEmail.error = "Kolom Email Tidak Boleh Kosong"
            edtEmail.requestFocus()
            return
        } else if(edtAlamat.text.isEmpty()) {
            edtAlamat.error = "Kolom Alamat Tidak Boleh Kosong"
            edtAlamat.requestFocus()
            return
        } else if(edtNohp.text.isEmpty()) {
            edtNohp.error = "Kolom Nomor Telepon Tidak Boleh Kosong"
            edtNohp.requestFocus()
            return
        }
        pb_loading.visibility = View.VISIBLE

        val id_user = sp.getUser()!!.id
        val user = User()
        user.id = id_user
        user.name = edtNama.text.toString()
        user.email = edtEmail.text.toString()
        user.password = edtPassword.text.toString()
//        user.foto = edt_layanan_edit_keterangan.text.toString()
        user.alamat = edtAlamat.text.toString()
        user.nohp = edtNohp.text.toString()
        if(fileImage == null){
            Toast.makeText(this, "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            pb_loading.visibility = View.GONE
            return
        }
        vm.update(user, fileImage!!)
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
        if(sp.getUser() == null){
            val intent =Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val user = sp.getUser()!!

        // Set Value
        var foto = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/storage/"+user.foto
        Picasso.get()
                .load(foto)
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .resize(500,500).centerInside()
                .into(imgFoto)
        var edtNama = findViewById<View>(R.id.edt_profil_edit_nama) as EditText
        edtNama.setText(user.name, TextView.BufferType.EDITABLE)
        var edtEmail = findViewById<View>(R.id.edt_profil_edit_email) as EditText
        edtEmail.setText(user.email, TextView.BufferType.EDITABLE)
        var edtAlamat = findViewById<View>(R.id.edt_profil_edit_alamat) as EditText
        edtAlamat.setText(user.alamat, TextView.BufferType.EDITABLE)
        var edtNohp = findViewById<View>(R.id.edt_profil_edit_nohp) as EditText
        edtNohp.setText(user.nohp, TextView.BufferType.EDITABLE)
    }

    fun obeservers(){
        vm.mData.observe(this, Observer {
            toast(""+it.message)
            val intent = Intent(this@ProfilEditActivity, MasukActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            pb_loading.visibility = View.GONE

        })

        vm.onFailure.observe(this, Observer {
            toast("error: $it")
            pb_loading.visibility = View.GONE
        })

    }

    private fun toast(string: String){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    fun setToolbar(){
        //set Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Edit Profil" // !! adalah null Exception
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}