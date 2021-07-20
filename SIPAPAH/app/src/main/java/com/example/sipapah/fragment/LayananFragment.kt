package com.example.sipapah.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapah.R
import com.example.sipapah.activity.*
import com.example.sipapah.adapter.AdapterKreasiLengkap
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.ResponModel
import kotlinx.android.synthetic.main.activity_masuk.*
import kotlinx.android.synthetic.main.fragment_layanan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LayananFragment : Fragment() {

    lateinit var btnPesan: LinearLayout
    lateinit var btnMenunggu: LinearLayout
    lateinit var btnDikonfirmasi: LinearLayout
    lateinit var btnSelesai: LinearLayout
    lateinit var btnDitolak: LinearLayout

    lateinit var sp: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_layanan, container, false)

        sp = SharedPref(requireActivity())


        btnPesan = view.findViewById(R.id.btn_pesanan)
        btnPesan.setOnClickListener{
            startActivity(Intent(requireActivity(), LayananActivity::class.java))
        }

        btnMenunggu = view.findViewById(R.id.btn_menunggu)
        btnMenunggu.setOnClickListener{
            startActivity(Intent(requireActivity(), LayananMenungguActivity::class.java))
        }
        btnDikonfirmasi = view.findViewById(R.id.btn_dikonfirmasi)
        btnDikonfirmasi.setOnClickListener{
            startActivity(Intent(requireActivity(), LayananDikonfirmasiActivity::class.java))
        }
        btnSelesai = view.findViewById(R.id.btn_selesai)
        btnSelesai.setOnClickListener{
            startActivity(Intent(requireActivity(), LayananSelesaiActivity::class.java))
        }
        btnDitolak = view.findViewById(R.id.btn_Ditolak)
        btnDitolak.setOnClickListener{
            startActivity(Intent(requireActivity(), LayananDitolakActivity::class.java))
        }

        return view
    }



}