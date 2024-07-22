package com.example.tasarimcalismasi_ii.data.entity

import java.io.Serializable

data class Geziler(var gezi_id       : String? = "",
                   var gezi_basla    : String? = "",
                   var gezi_bitis    : String? = "",
                   var gezi_saat     : String? = "",
                   var gezi_tarih    : String? = "",
                   var gezi_ucret    : String? = "",
                   var gezi_iletisim : String? = "",
                   val gezi_bilgi    : String? = "",
                   var gezi_kontenjan : String? = "",
                   var eski_ucret: String? = "", // Eski fiyatı tutmak için
                   var eski_kontenjan: String? = "" ,
                   var initialKontenjan: String? = ""): Serializable{// Eski kontenjanı tutmak için)

/*
    init {
        initialKontenjan = gezi_kontenjan // İlk kontenjanı sakla
    }*/

    fun updateKontenjan() {
        val currentKontenjan = gezi_kontenjan?.toInt() ?: 0
        if (currentKontenjan > 0) {
            eski_kontenjan = gezi_kontenjan // Eski kontenjanı sakla
            gezi_kontenjan = (currentKontenjan - 1).toString()
        }
    }

    fun updateUcret() {
        val currentKontenjan = gezi_kontenjan?.toInt() ?: 0
        if (currentKontenjan > 0) {
            eski_ucret = gezi_ucret // Eski fiyatı sakla
            val originalPrice = gezi_ucret?.toDouble() ?: 0.0
            val newPrice = (originalPrice * 0.97).toInt() // Örnek: %10 azalt ve tam sayıya yuvarla
            gezi_ucret = newPrice.toString()
        }
    }
/*
    fun revertChanges() {
        val currentKontenjan = gezi_kontenjan?.toInt() ?: 0
        val originalKontenjan = eski_kontenjan?.toInt() ?: 0

        if (currentKontenjan < originalKontenjan) {
            gezi_kontenjan = (currentKontenjan + 1).toString() // Kontenjanı bir artır
        }

        gezi_ucret = eski_ucret // Eski fiyata geri dön
    }*/
    fun revertChanges() {
        gezi_kontenjan = (eski_kontenjan?.toInt()?.plus(0) ?: 1).toString() // Kontenjanı bir artır
        gezi_ucret = eski_ucret // Eski fiyata geri dön
    }
}
