package com.example.sipapah.layananActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sipapah.model.Layanan
import com.example.sipapah.model.ResponModel
import java.io.File

class LayananMenungguEditViewModel (application: Application) : AndroidViewModel(application){

    private val repo = LayananMenungguEditRepository(application)
    val showProgress: LiveData<Boolean>
    val mData: LiveData<ResponModel>
    val onFailure: LiveData<String>

    init {
        this.showProgress = repo.showProgress
        this.onFailure = repo.onFailure
        this.mData = repo.mData
    }

    fun update(data: Layanan, file: File){
        repo.update(data, file)
    }
}