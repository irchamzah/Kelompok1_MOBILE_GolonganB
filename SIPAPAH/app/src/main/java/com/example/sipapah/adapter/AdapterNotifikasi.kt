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
import com.example.sipapah.model.Notifikasi
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class AdapterNotifikasi(var activity: Context, var arrDataNotifikasi:ArrayList<Notifikasi>):RecyclerView.Adapter<AdapterNotifikasi.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvJudul = view.findViewById<TextView>(R.id.tv_judulNotifikasi)
        val tvKeterangan = view.findViewById<TextView>(R.id.tv_keteranganNotifikasi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_notifikasi, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataNotifikasi.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvJudul.text = arrDataNotifikasi[position].title
        holder.tvKeterangan.text = arrDataNotifikasi[position].keterangan
    }


}