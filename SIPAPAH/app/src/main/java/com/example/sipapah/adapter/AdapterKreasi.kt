package com.example.sipapah.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.activity.DetailKreasiActivity
import com.example.sipapah.activity.LoginActivity
import com.example.sipapah.model.Kreasi
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class AdapterKreasi(var activity: Context, var arrDataKreasi:ArrayList<Kreasi>):RecyclerView.Adapter<AdapterKreasi.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val imgKreasi = view.findViewById<ImageView>(R.id.img_kreasi)
        val tvKeterangan = view.findViewById<TextView>(R.id.tv_keterangan)
        val cvItemKreasi = view.findViewById<ImageView>(R.id.img_kreasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kreasi, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataKreasi.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = arrDataKreasi[position].nama
        holder.tvKeterangan.text = arrDataKreasi[position].keterangan
        val image = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/IMG/fotokreasi/"+arrDataKreasi[position].foto
        Picasso.get()
            .load(image).resize(500,500).centerInside()
            .placeholder(R.drawable.sipapa_hijau)
            .error(R.drawable.sipapa_hijau)
            .into(holder.imgKreasi)
        holder.cvItemKreasi.setOnClickListener{
            var Data = Intent(activity, DetailKreasiActivity::class.java) //kirim Data ke DetailKreasiActivity
            val dataBerdasarkanPosisi = Gson().toJson(arrDataKreasi[position], Kreasi::class.java) //diganti ke String
            Data.putExtra("dataKreasi", dataBerdasarkanPosisi)
            activity.startActivity(Data)
        }

    }
}