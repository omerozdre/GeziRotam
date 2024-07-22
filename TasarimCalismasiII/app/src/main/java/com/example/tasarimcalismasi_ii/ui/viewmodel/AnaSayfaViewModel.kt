package com.example.tasarimcalismasi_ii.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasarimcalismasi_ii.data.entity.Geziler
import com.example.tasarimcalismasi_ii.data.repo.GezilerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var grepo:GezilerRepository) : ViewModel() {
    var gezilerListesi = MutableLiveData<List<Geziler>>()

    init {
        gezileriYukle() //AnaSayfaViewModel ilk calıstıgı anda gezileriYukle() fonk calıstırsın veri getirsin
    }

    fun geziSil(gezi_id: String) {
        grepo.geziSil(gezi_id)
    }

    fun gezileriYukle() {
        gezilerListesi = grepo.gezileriYukle()
    }

    fun ara(aramaKelimesi:String){
        gezilerListesi = grepo.ara(aramaKelimesi)
    }

  /*  fun basvur(gezi_id: String) {
        grepo.basvur(gezi_id)
    }*/

    fun geziGuncelle(gezi: Geziler) {
        grepo.geziGuncelle(gezi)
    }

    }
