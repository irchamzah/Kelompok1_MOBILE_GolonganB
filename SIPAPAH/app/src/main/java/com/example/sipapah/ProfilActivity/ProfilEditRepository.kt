package com.example.sipapah.layananActivity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.sipapah.app.ApiConfig
import com.example.sipapah.helper.SharedPref
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.ResponModel
import com.example.sipapah.model.User
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfilEditRepository(var application: Application){

    lateinit var sp:SharedPref

    val showProgress = MutableLiveData<Boolean>()
    val onFailure = MutableLiveData<String>()
    var mData = MutableLiveData<ResponModel>()

//    fun changeProgress(){
//        showProgress.value = !(showProgress.value != null && showProgress.value!!)
//    }

    fun update(data: User, file: File){
        val id = data.id
        val namaUser = convert(data.name)
        val emailUser = convert(data.email)
        val passwordUser = convert(data.password)
        val alamatUser = convert(data.alamat)
        val nohpUser = convert(data.nohp)

        val fileImage = convertFile(file)

        ApiConfig.instanceRetrofit.setprofiledit(id,namaUser,emailUser,passwordUser, alamatUser, nohpUser, fileImage).enqueue(object : Callback<ResponModel> {

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                if(response.isSuccessful) {
                    val res = response.body()!!
                    if (res.success == 1){
                        mData.value = res
                    } else {
                        onFailure.value = res.message
                    }
                } else {
                    onFailure.value = response.message()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                showProgress.value = false
                onFailure.value = t.message
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

}