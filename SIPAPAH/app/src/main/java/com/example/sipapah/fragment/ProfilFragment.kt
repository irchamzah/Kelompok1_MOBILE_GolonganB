package com.example.sipapah.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.activity.LayananActivity
import com.example.sipapah.activity.LoginActivity
import com.example.sipapah.activity.ProfilEditActivity
import com.example.sipapah.helper.SharedPref
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {

    lateinit var sp:SharedPref
    lateinit var btnEditProfil: RelativeLayout
    lateinit var btnLogout:LinearLayout

    lateinit var tvNama:TextView
    lateinit var tvEmail:TextView
    lateinit var imgFoto:ImageView
    lateinit var tvAlamat:TextView
    lateinit var tvNohp:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        sp = SharedPref(requireActivity())

        tvNama = view.findViewById(R.id.tv_nama_user)
        tvEmail = view.findViewById(R.id.tv_email_user)
        imgFoto = view.findViewById(R.id.img_fotouser)

        setData()

        btnEditProfil = view.findViewById(R.id.btn_editProfile)
        btnEditProfil.setOnClickListener{
            startActivity(Intent(requireActivity(), ProfilEditActivity::class.java))
        }
        btnLogout = view.findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener{
            sp.setStatusLogin(false)
            val intent =Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return view
    }

    fun setData(){
        if(sp.getUser() == null){
            val intent =Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val user = sp.getUser()!!
        tvNama.text = user.name
        tvEmail.text = user.email
        var foto = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/IMG/fotoprofil/"+user.foto
        Picasso.get()
                .load(foto!!)
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .resize(500,500).centerInside()
                .into(imgFoto)
    }
}