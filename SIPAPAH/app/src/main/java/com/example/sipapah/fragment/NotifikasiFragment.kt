package com.example.sipapah.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.activity.LoginActivity
import com.example.sipapah.adapter.AdapterKreasiLengkap
import com.example.sipapah.adapter.AdapterNotifikasi
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Kreasi
import com.example.sipapah.model.Notifikasi
import com.example.sipapah.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [NotifikasiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotifikasiFragment : Fragment() {

    lateinit var rvNotifikasi: RecyclerView
    lateinit var sp:SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notifikasi, container, false)

        init(view)

        sp = SharedPref(requireActivity())

        getNotifikasi()

        return view
    }

    fun init(view: View) {
        rvNotifikasi = view.findViewById(R.id.rv_notifikasi)
    }

    private var listNotifikasi: ArrayList<Notifikasi> = ArrayList()

    fun getNotifikasi() {

        if(sp.getUser() == null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val id = sp.getUser()!!.id

//        val id = SharedPref(requireActivity()).getUser()!!.id

        ApiConfig.instanceRetrofit.getnotifikasi(id).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    listNotifikasi = respon.notifikasis
                    displayNotifikasi()
                }

            }
        })
    }

    fun displayNotifikasi() {
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvNotifikasi.adapter = AdapterNotifikasi(requireActivity(),listNotifikasi)
        rvNotifikasi.layoutManager = layoutManager

    }

}