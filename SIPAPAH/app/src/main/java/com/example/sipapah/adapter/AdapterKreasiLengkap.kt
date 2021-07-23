package com.example.sipapah.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.activity.DetailKreasiActivity
import com.example.sipapah.model.Kreasi
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class AdapterKreasiLengkap(var activity: Context, var arrDataKreasi:ArrayList<Kreasi>):RecyclerView.Adapter<AdapterKreasiLengkap.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_namaLengkap)
        val imgKreasi = view.findViewById<ImageView>(R.id.img_kreasiLengkap)
        val tvKeterangan = view.findViewById<TextView>(R.id.tv_keteranganLengkap)
        val cvItemKreasiLengkap = view.findViewById<ImageView>(R.id.img_kreasiLengkap)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kreasi_lengkap, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataKreasi.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = arrDataKreasi[position].nama
        holder.tvKeterangan.text = arrDataKreasi[position].keterangan
//        holder.imgKreasi.setImageResource(arrDataKreasi[position].foto)
        val image = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/IMG/fotokreasi/"+arrDataKreasi[position].foto
        Picasso.get()
            .load(image).resize(500,500).centerInside()
            .placeholder(R.drawable.sipapa_hijau)
            .error(R.drawable.sipapa_hijau)
            .into(holder.imgKreasi)
        holder.cvItemKreasiLengkap.setOnClickListener{
            var Data = Intent(activity, DetailKreasiActivity::class.java) //kirim Data ke DetailKreasiActivity
            val dataBerdasarkanPosisi = Gson().toJson(arrDataKreasi[position], Kreasi::class.java) //diganti ke String
            Data.putExtra("dataKreasi", dataBerdasarkanPosisi)
            activity.startActivity(Data)
        }
    }


}