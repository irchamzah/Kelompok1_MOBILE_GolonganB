package com.example.sipapah.layananActivity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.ResponModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class LayananRepository(var application: Application){



    lateinit var sp:SharedPref

    val showProgress = MutableLiveData<Boolean>()
    val onFailure = MutableLiveData<String>()
    var mData = MutableLiveData<ResponModel>()

    fun changeProgress(){
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun create(data: Layanan, file: File){


        val id = data.id
        val categoryId = convert(data.category_id)
        val tanggalJemput = convert(data.tanggaljemput)
        val dataKeterangan = convert(data.keterangan)

        val fileImage = convertFile(file)

        ApiConfig.instanceRetrofit.setmemesan(id,categoryId,tanggalJemput,dataKeterangan, fileImage).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                showProgress.value = false
                onFailure.value = t.message
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                if(response.isSuccessful) {
                    val res = response.body()!!
                    if (res.success == 1){

                        mData.value = res
                    }
                    else {
                        onFailure.value = res.message
                    }
                } else {
                    onFailure.value = response.message()
                }
            }
        })

    }

    private fun convert(string: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), string)
    }

    private  fun convertFile(file: File): MultipartBody.Part {
        val reqFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData("path", file.name, reqFile)
    }




//    private fun login(conf: Call<ResponModel>){
//        conf.enqueue(object : Callback<ResponModel> {
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                showProgress.value = false
//                onFailure.value = t.message
//            }
//
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                showProgress.value = false
//                if(response.isSuccessful) {
//                    val res = response.body()!!
//                    if (res.success == 1) mData.value = res
//                    else onFailure.value = res.message
//                } else onFailure.value = response.message()
//            }
//        })
//    }
}