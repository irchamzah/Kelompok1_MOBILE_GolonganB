package com.example.sipapah.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.activity.DetailKreasiActivity
import com.example.sipapah.activity.LayananMenungguActivity
import com.example.sipapah.activity.LayananMenungguEditActivity
import com.example.sipapah.activity.LayananSelesaiActivity
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Kreasi
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.Notifikasi
import com.example.sipapah.model.ResponModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterLayananSelesai(var activity: Context, var arrDataLayananSelesai:ArrayList<Layanan>):RecyclerView.Adapter<AdapterLayananSelesai.Holder>() {

    lateinit var sp: SharedPref
    var namakategori = ""
    var namastatus = ""

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val imgLayananSelesai = view.findViewById<ImageView>(R.id.img_layanan_selesai)
        val tvLayananSelesaiKategori = view.findViewById<TextView>(R.id.tv_layanan_selesai_kategori)
        val tvLayananSelesaiTanggalJemput = view.findViewById<TextView>(R.id.tv_layanan_selesai_tanggaljemput)
        val tvLayananSelesaiKeterangan = view.findViewById<TextView>(R.id.tv_layanan_selesai_keterangan_)
        val tvLayananSelesaiStatus = view.findViewById<TextView>(R.id.tv_layanan_selesai_status)
        val tvLayananSelesaiPendapatan = view.findViewById<TextView>(R.id.tv_layanan_selesai_pendapatan)
        val icLayananHapus = view.findViewById<ImageView>(R.id.ic_layanan_selesai_hapus)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layanan_selesai, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrDataLayananSelesai.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var kategoriid = arrDataLayananSelesai[position].category_id
        if(kategoriid == 1){
            namakategori = "Kertas"
        } else if (kategoriid == 2){
            namakategori = "Kardus"
        } else if (kategoriid == 3){
            namakategori = "Plastik"
        }

        var statusid = arrDataLayananSelesai[position].status_id
        if(statusid == "1"){
            namastatus = "Menunggu Konfirmasi"
        } else if (statusid == "2"){
            namastatus = "Dikonfirmasi"
        } else if (statusid == "3"){
            namastatus = "Selesai"
        } else if (statusid == "4"){
            namastatus = "Ditolak"
        }




        val image = "http://192.168.1.25/Kelompok1_WEB_GolonganB/public/storage/"+arrDataLayananSelesai[position].file
        Picasso.get()
                .load(image).resize(500,500).centerInside()
                .placeholder(R.drawable.sipapa_hijau)
                .error(R.drawable.sipapa_hijau)
                .into(holder.imgLayananSelesai)
        holder.tvLayananSelesaiKategori.text = namakategori
        holder.tvLayananSelesaiTanggalJemput.text = arrDataLayananSelesai[position].tanggaljemput
        holder.tvLayananSelesaiKeterangan.text = arrDataLayananSelesai[position].keterangan
        holder.tvLayananSelesaiStatus.text = namastatus
        holder.tvLayananSelesaiPendapatan.text = arrDataLayananSelesai[position].pendapatan
        holder.icLayananHapus.setOnClickListener{
            fun hapuspesanan(){
                sp = SharedPref(activity as Activity)

                val layanan_id = arrDataLayananSelesai[position].id
                val file = arrDataLayananSelesai[position].file
                ApiConfig.instanceRetrofit.hapuslayananselesai(
                    layanan_id,
                    file
                ).enqueue(object : Callback<ResponModel> {
                    override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    val respon = response.body()!!
                    if (respon.success == 1) {

                    } else {

                    }
                }
                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                }
                })
            }
            hapuspesanan()
            var Data = Intent(activity, LayananSelesaiActivity::class.java)
            Data.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(Data)
        }
    }


}