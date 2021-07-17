package com.example.sipapah.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.sipapah.MainActivity
import com.example.sipapah.R
import com.example.sipapah.adapter.AdapterKreasi
import com.example.sipapah.adapter.AdapterSlider
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.model.Kreasi
import com.example.sipapah.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvKreasi: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false) //Menampilkan fragment home

        init(view)

        getKreasi()

        return view
    }


    fun init(view: View) {
        vpSlider = view.findViewById(R.id.vp_slider)
        rvKreasi = view.findViewById(R.id.rv_kreasi)
    }

    fun displayKreasi() {
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider1)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvKreasi.adapter = AdapterKreasi(requireActivity(), listKreasi)
        rvKreasi.layoutManager = layoutManager

    }

    private var listKreasi: ArrayList<Kreasi> = ArrayList()    // Membuat Wadah Array Baru
    //  Mengambil Isi dari tabel Kreasi
    fun getKreasi() {
        ApiConfig.instanceRetrofit.getkreasi().enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1){
                    listKreasi = respon.kreasis
                    displayKreasi()
                }

            }
        })

//    val arrKreasi: ArrayList<Kreasi>get(){
//        val arr = ArrayList<Kreasi>()
//
//        val k1 = Kreasi()
//        k1.nama = "Nama Tutorial 1"
//        k1.foto = R.drawable.slider1
//        k1.keterangan = "Keterangan 1"
//        k1.keterangan_detail = "Keterangan detail 1"
//
//        val k2 = Kreasi()
//        k2.nama = "Nama Tutorial 2"
//        k2.foto = R.drawable.slider2
//        k2.keterangan = "Keterangan 2"
//        k2.keterangan_detail = "Keterangan detail 2"
//
//        val k3 = Kreasi()
//        k3.nama = "Nama Tutorial 3"
//        k3.foto = R.drawable.slider3
//        k3.keterangan = "Keterangan 3"
//        k3.keterangan_detail = "Keterangan detail 3"
//
//        arr.add(k1)
//        arr.add(k2)
//        arr.add(k3)
//
//
//        return arr
//    }


    }
}