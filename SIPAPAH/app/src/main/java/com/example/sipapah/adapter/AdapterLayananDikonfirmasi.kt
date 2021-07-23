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
import com.example.sipapah.activity.LayananMenungguActivity
import com.example.sipapah.activity.LayananMenungguEditActivity
import com.example.sipapah.model.Kreasi
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.Notifikasi
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class AdapterLayananDikonfirmasi(var activity: Context, var arrDataLayananDikonfirmasi:ArrayList<Layanan>):RecyclerView.Adapter<AdapterLayananDikonfirmasi.Holder>() {

    var namakategori = ""
    var namastatus = ""

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val imgLayananDikonfirmasi = view.findViewById<ImageView>(R.id.img_layanan_dikonfirmasi)
        val tvLayananDikonfirmasiKategori = view.findViewById<TextView>(R.id.tv_layanan_dikonfirmasi_kategori)
        val tvLayananDikonfirmasiTanggalJemput = view.findViewById<TextView>(R.id.tv_layanan_dikonfirmasi_tanggaljemput)
        val tvLayananDikonfirmasiKeterangan = view.findViewById<TextView>(R.id.tv_layanan_dikonfirmasi_keterangan_)
        val tvLayananDikonfirmasiStatus = view.findViewById<TextView>(R.id.tv_layanan_dikonfirmasi_status)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layanan_dikonfirmasi, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataLayananDikonfirmasi.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var kategoriid = arrDataLayananDikonfirmasi[position].category_id
        if(kategoriid == 1){
            namakategori = "Kertas"
        } else if (kategoriid == 2){
            namakategori = "Kardus"
        } else if (kategoriid == 3){
            namakategori = "Plastik"
        }

        var statusid = arrDataLayananDikonfirmasi[position].status_id
        if(statusid == "1"){
            namastatus = "Menunggu Konfirmasi"
        } else if (statusid == "2"){
            namastatus = "Dikonfirmasi"
        } else if (statusid == "3"){
            namastatus = "Selesai"
        } else if (statusid == "4"){
            namastatus = "Ditolak"
        }

        val image = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/IMG/fotopesanan/"+arrDataLayananDikonfirmasi[position].file
        Picasso.get()
                .load(image).resize(500,500).centerInside()
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .into(holder.imgLayananDikonfirmasi)
        holder.tvLayananDikonfirmasiKategori.text = namakategori
        holder.tvLayananDikonfirmasiTanggalJemput.text = arrDataLayananDikonfirmasi[position].tanggaljemput
        holder.tvLayananDikonfirmasiKeterangan.text = arrDataLayananDikonfirmasi[position].keterangan
        holder.tvLayananDikonfirmasiStatus.text = namastatus
    }

}