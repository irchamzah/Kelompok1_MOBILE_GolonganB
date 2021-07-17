package com.example.sipapah.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.activity.LoginActivity
import com.example.sipapah.helper.SharedPref


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {

    lateinit var sp:SharedPref
    lateinit var btnLogout:Button

    lateinit var tvNama:TextView
    lateinit var tvEmail:TextView
    lateinit var imgFoto:ImageView
    lateinit var tvAlamat:TextView
    lateinit var tvNohp:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)

        init(view)

        sp = SharedPref(requireActivity())

        btnLogout.setOnClickListener{
            sp.setStatusLogin(false)
            val intent =Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setData()

        return view
    }

    private fun init(view:View){
        btnLogout = view.findViewById(R.id.btn_logout)

        tvNama = view.findViewById(R.id.tv_nama)
        tvEmail = view.findViewById(R.id.tv_email)
//        tvNohp = view.findViewById(R.id.tv_phone)
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
//        tvNohp.text = user.nohp

    }



}