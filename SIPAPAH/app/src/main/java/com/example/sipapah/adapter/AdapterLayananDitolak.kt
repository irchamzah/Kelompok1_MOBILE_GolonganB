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

class AdapterLayananDitolak(var activity: Context, var arrDataLayananDitolak:ArrayList<Layanan>):RecyclerView.Adapter<AdapterLayananDitolak.Holder>() {

    var namakategori = ""
    var namastatus = ""

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val imgLayananDitolak = view.findViewById<ImageView>(R.id.img_layanan_ditolak)
        val tvLayananDitolakKategori = view.findViewById<TextView>(R.id.tv_layanan_ditolak_kategori)
        val tvLayananDitolakTanggalJemput = view.findViewById<TextView>(R.id.tv_layanan_ditolak_tanggaljemput)
        val tvLayananDitolakKeterangan = view.findViewById<TextView>(R.id.tv_layanan_ditolak_keterangan_)
        val tvLayananDitolakStatus = view.findViewById<TextView>(R.id.tv_layanan_ditolak_status)
        val tvLayananDitolakKeteranganTolak = view.findViewById<TextView>(R.id.tv_layanan_ditolak_keterangan_tolak)
        val icLayananHapus = view.findViewById<ImageView>(R.id.ic_layanan_ditolak_hapus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layanan_ditolak, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataLayananDitolak.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var kategoriid = arrDataLayananDitolak[position].category_id
        if(kategoriid == "1"){
            namakategori = "Kertas"
        } else if (kategoriid == "2"){
            namakategori = "Kardus"
        } else if (kategoriid == "3"){
            namakategori = "Plastik"
        }

        var statusid = arrDataLayananDitolak[position].status_id
        if(statusid == "1"){
            namastatus = "Menunggu Konfirmasi"
        } else if (statusid == "2"){
            namastatus = "Dikonfirmasi"
        } else if (statusid == "3"){
            namastatus = "Selesai"
        } else if (statusid == "4"){
            namastatus = "Ditolak"
        }




        val image = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/storage/"+arrDataLayananDitolak[position].file
        Picasso.get()
                .load(image).resize(500,500).centerInside()
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .into(holder.imgLayananDitolak)
        holder.tvLayananDitolakKategori.text = namakategori
        holder.tvLayananDitolakTanggalJemput.text = arrDataLayananDitolak[position].tanggaljemput
        holder.tvLayananDitolakKeterangan.text = arrDataLayananDitolak[position].keterangan
        holder.tvLayananDitolakStatus.text = namastatus
        holder.tvLayananDitolakKeteranganTolak.text = arrDataLayananDitolak[position].keterangan
        holder.icLayananHapus.setOnClickListener{
//            var Data = Intent(activity, LayananMenungguEditActivity::class.java) //kirim Data ke DetailKreasiActivity
//            val dataBerdasarkanPosisi = Gson().toJson(arrDataLayananSelesai[position], Layanan::class.java) //diganti ke String
//            Data.putExtra("dataLayananMenunggu", dataBerdasarkanPosisi)
//            activity.startActivity(Data)
        }
    }


}