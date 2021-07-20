package com.example.sipapah.model

class ResponModel {
    var success = 0
    lateinit var message:String
    var user = User()
    var kreasis: ArrayList<Kreasi> = ArrayList()
    var notifikasis: ArrayList<Notifikasi> = ArrayList()
    var layanan = Layanan()
    var layanans: ArrayList<Layanan> = ArrayList()

}