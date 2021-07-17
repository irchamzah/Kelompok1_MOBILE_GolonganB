package com.example.sipapah.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.adapter.AdapterKreasi
import com.example.sipapah.adapter.AdapterKreasiLengkap
import com.example.sipapah.adapter.AdapterSlider
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.model.Kreasi
import com.example.sipapah.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [KreasiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KreasiFragment : Fragment() {

    lateinit var rvKreasi: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_kreasi, container, false)// Menampilkan fragment kreasi

        init(view)

        setMemesan()

        return view
    }

    fun init(view: View) {
        rvKreasi = view.findViewById(R.id.rv_kreasi_lengkap)
    }

    private var listKreasi: ArrayList<Kreasi> = ArrayList()

    fun setMemesan() {
        ApiConfig.instanceRetrofit.getkreasi().enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    listKreasi = respon.kreasis
                    displayKreasi()
                }

            }
        })
    }

    fun displayKreasi() {
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvKreasi.adapter = AdapterKreasiLengkap(requireActivity(),listKreasi)
        rvKreasi.layoutManager = layoutManager

    }
}