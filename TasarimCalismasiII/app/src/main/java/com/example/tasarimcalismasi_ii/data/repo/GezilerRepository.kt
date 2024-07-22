package com.example.tasarimcalismasi_ii.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.tasarimcalismasi_ii.data.datasource.GezilerDataSource
import com.example.tasarimcalismasi_ii.data.entity.Geziler
import com.example.tasarimcalismasi_ii.data.entity.User
import com.google.firebase.firestore.FirebaseFirestore

class GezilerRepository(var gds : GezilerDataSource){


    fun Kaydet(gezi_basla : String, gezi_bitis : String, gezi_saat  : String, gezi_tarih : String,
               gezi_ucret : String ,gezi_bilgi : String,gezi_iletisim : String, gezi_kontenjan : String)
    = gds.Kaydet(gezi_basla,gezi_bitis,gezi_saat,gezi_tarih,gezi_ucret,gezi_bilgi,gezi_iletisim,gezi_kontenjan)

    fun geziSil(gezi_id:String)
    = gds.geziSil(gezi_id)

    fun gezileriYukle() : MutableLiveData<List<Geziler>>
    = gds.gezileriYukle()

    fun ara(aramaKelimesi : String): MutableLiveData<List<Geziler>>
    = gds.ara(aramaKelimesi)

   /* fun basvur(gezi_id: String) = gds.basvur(gezi_id)*/

    fun geziGuncelle(gezi: Geziler) = gds.geziGuncelle(gezi)
}
