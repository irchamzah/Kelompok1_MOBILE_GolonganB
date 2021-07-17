package com.example.sipapah.app

import com.example.sipapah.model.ResponModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.text.DateFormat

interface ApiService{

    @FormUrlEncoded
    @POST("register")
    fun register(

        @Field("name") name:String,
        @Field("email") email:String,
        @Field("nohp") nohp:String,
        @Field("alamat") alamat:String,
        @Field("password") password:String

        ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(

        @Field("email") email:String,
        @Field("password") password:String

        ):Call<ResponModel>

    @GET("kreasi")
    fun getkreasi():Call<ResponModel>

    @GET("notifikasi/user/{id}")
    fun getnotifikasi(
            @Path("id") id: Int
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("layanan/pesan/{id}")
    fun setmemesan(
            @Path("id") id: Int,

            @Field("category") category:String,
            @Field("file") file:String,
            @Field("tanggaljemput") tanggaljemput:String,
            @Field("keterangan") keterangan:String

    ):Call<ResponModel>

}