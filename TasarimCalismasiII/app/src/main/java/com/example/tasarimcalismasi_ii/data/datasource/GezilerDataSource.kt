package com.example.tasarimcalismasi_ii.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tasarimcalismasi_ii.data.entity.Geziler
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GezilerDataSource(var collectionGeziler:CollectionReference) {//kayıt yapmamızı sağlıyor
    var gezilerListesi = MutableLiveData<List<Geziler>>() //hem yuklemede hem de arama işlemlerinde kullancağım

    fun gezileriYukle() : MutableLiveData<List<Geziler>> {
        collectionGeziler.addSnapshotListener { value, error ->//gercek zamanlı calısmak icin silme güncelleme eklemde calısacak altyapı
            if (value !=null){
                val liste = ArrayList<Geziler>()

                for(d in value.documents){
                    val gezi = d.toObject(Geziler::class.java)
                    if(gezi != null){
                        gezi.gezi_id = d.id
                        liste.add(gezi)
                    }
                }

                gezilerListesi.value = liste
            }
        }

        return gezilerListesi
    }

    fun ara(aramaKelimesi:String): MutableLiveData<List<Geziler>> {
        collectionGeziler.addSnapshotListener { value, error ->//gercek zamanlı calısmak icin silme güncelleme eklemde calısacak altyapı
            if (value !=null){
                val liste = ArrayList<Geziler>()

                for(d in value.documents){
                    val gezi = d.toObject(Geziler::class.java)
                    if(gezi != null){
                        if(gezi.gezi_basla!!.lowercase().contains(aramaKelimesi.lowercase())){
                            gezi.gezi_id = d.id
                            liste.add(gezi)
                        }
                    }
                }
                gezilerListesi.value = liste
            }
        }

        return gezilerListesi
    }

    //geri dönüş degeri olmadığı için return koymuyoruz
    fun Kaydet(gezi_basla : String, gezi_bitis : String, gezi_saat  : String, gezi_tarih : String, gezi_ucret : String ,
               gezi_bilgi : String,gezi_iletisim : String,gezi_kontenjan :String){
        val yeniGezi = Geziler("",gezi_basla,gezi_bitis,gezi_saat,gezi_tarih,gezi_ucret,gezi_bilgi,gezi_iletisim,gezi_kontenjan)
        collectionGeziler.document().set(yeniGezi)
    }

    fun geziSil(gezi_id:String){
        collectionGeziler.document(gezi_id).delete()
    }

    fun geziGuncelle(gezi: Geziler) {
        collectionGeziler.document(gezi.gezi_id!!).set(gezi)
    }

  /*  fun basvur(gezi_id: String) {
        collectionGeziler.document(gezi_id).get().addOnSuccessListener { document ->
            val gezi = document.toObject(Geziler::class.java)
            gezi?.let {
                it.updateKontenjan()
                it.updateUcret()
                collectionGeziler.document(gezi_id).set(it)
            }
        }
    }*/
}