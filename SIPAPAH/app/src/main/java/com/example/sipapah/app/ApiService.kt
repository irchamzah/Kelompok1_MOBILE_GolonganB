package com.example.sipapah.app

import com.example.sipapah.model.ResponModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService{

    @Multipart
    @POST("layanan/pesan/{id}")
    fun setmemesan(


            @Path("id") id: Int,

            @Part("category_id") category_id: RequestBody,

            @Part("tanggaljemput") tanggaljemput: RequestBody,
            @Part("keterangan") keterangan: RequestBody,
            @Part path: MultipartBody.Part? = null,

            ): Call<ResponModel>

    @GET("layanan/riwayat/menunggu/{id}")
    fun getlayanan(
            @Path("id") id: Int
    ):Call<ResponModel>

    @Multipart
    @POST("layanan/riwayat/menunggu/update/{id}")
    fun setlayananedit(

            @Path("id") id: Int,
            @Part("category_id") category_id: RequestBody,
            @Part("tanggaljemput") tanggaljemput: RequestBody,
            @Part("keterangan") keterangan: RequestBody,
            @Part path: MultipartBody.Part? = null,

            ): Call<ResponModel>

    @GET("layanan/riwayat/dikonfirmasi/{id}")
    fun getlayanandikonfirmasi(
            @Path("id") id: Int
    ):Call<ResponModel>

    @GET("layanan/riwayat/selesai/{id}")
    fun getlayananselesai(
            @Path("id") id: Int
    ):Call<ResponModel>

    @GET("layanan/riwayat/ditolak/{id}")
    fun getlayananditolak(
            @Path("id") id: Int
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("register")
    fun register(

        @Field("name") name: String,
        @Field("email") email: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String

    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(

        @Field("email") email: String,
        @Field("password") password: String

    ):Call<ResponModel>

    @GET("kreasi")
    fun getkreasi():Call<ResponModel>

    @GET("notifikasi/user/{id}")
    fun getnotifikasi(
        @Path("id") id: Int
    ):Call<ResponModel>









//    @Multipart
//    @POST("layanan/pesan/{id}")
//    fun setmemesan(
//        @Part part: MultipartBody.Part,
//        @Part("id") id: RequestBody,
//        @Part("category") category: RequestBody,
//        @Part("file") file: RequestBody,
//        @Part("tanggaljemput") tanggaljemput: RequestBody,
//        @Part("keterangan") keterangan: RequestBody
//
//    )


}