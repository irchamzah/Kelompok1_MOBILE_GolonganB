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

class AdapterLayananMenunggu(var activity: Context, var arrDataLayananMenunggu:ArrayList<Layanan>):RecyclerView.Adapter<AdapterLayananMenunggu.Holder>() {

    var namakategori = ""
    var namastatus = ""

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val imgLayananMenunggu = view.findViewById<ImageView>(R.id.img_layanan_menunggu)
        val tvLayananMenungguKategori = view.findViewById<TextView>(R.id.tv_layanan_menunggu_kategori)
        val tvLayananMenungguTanggalJemput = view.findViewById<TextView>(R.id.tv_layanan_menunggu_tanggaljemput)
        val tvLayananMenungguKeterangan = view.findViewById<TextView>(R.id.tv_layanan_menunggu_keterangan_)
        val tvLayananMenungguStatus = view.findViewById<TextView>(R.id.tv_layanan_menunggu_status)
        val icLayananEdit = view.findViewById<ImageView>(R.id.ic_layanan_menunggu_edit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layanan_menunggu, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataLayananMenunggu.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var kategoriid = arrDataLayananMenunggu[position].category_id
        if(kategoriid == 1){
            namakategori = "Kertas"
        } else if (kategoriid == 2){
            namakategori = "Kardus"
        } else if (kategoriid == 3){
            namakategori = "Plastik"
        }

        var statusid = arrDataLayananMenunggu[position].status_id
        if(statusid == "1"){
            namastatus = "Menunggu Konfirmasi"
        } else if (statusid == "2"){
            namastatus = "Dikonfirmasi"
        } else if (statusid == "3"){
            namastatus = "Selesai"
        } else if (statusid == "4"){
            namastatus = "Ditolak"
        }

        val image = "https://wsjti.id/sipapah/public/img/fotopesanan/"+arrDataLayananMenunggu[position].file
        Picasso.get()
                .load(image).resize(500,500).centerInside()
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .into(holder.imgLayananMenunggu)
        holder.tvLayananMenungguKategori.text = namakategori
        holder.tvLayananMenungguTanggalJemput.text = arrDataLayananMenunggu[position].tanggaljemput
        holder.tvLayananMenungguKeterangan.text = arrDataLayananMenunggu[position].keterangan
        holder.tvLayananMenungguStatus.text = namastatus
        holder.icLayananEdit.setOnClickListener{
            var Data = Intent(activity, LayananMenungguEditActivity::class.java) //kirim Data ke DetailKreasiActivity
            val dataBerdasarkanPosisi = Gson().toJson(arrDataLayananMenunggu[position], Layanan::class.java) //diganti ke String
            Data.putExtra("dataLayananMenunggu", dataBerdasarkanPosisi)
            activity.startActivity(Data)
        }
    }


}