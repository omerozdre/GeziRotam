package com.example.tasarimcalismasi_ii.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasarimcalismasi_ii.data.repo.GezilerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeziOlusturViewModel @Inject constructor(var grepo:GezilerRepository): ViewModel() {
    /*
    fun Kaydet(gezi_basla : String, gezi_bitis : String, gezi_saat  : String, gezi_tarih : String,
               gezi_ucret : String ,gezi_bilgi : String,gezi_iletisim : String){

        grepo.Kaydet(gezi_basla,gezi_bitis,gezi_saat,gezi_tarih,gezi_ucret,gezi_bilgi,gezi_iletisim)
    }

}*/

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun Kaydet(
        gezi_basla: String, gezi_bitis: String, gezi_saat: String, gezi_tarih: String,
        gezi_ucret: String, gezi_bilgi: String, gezi_iletisim: String,gezi_kontenjan: String
    ) {
        when {
            gezi_basla.isEmpty() -> _toastMessage.value = "Başlangıç yeri boş olamaz."
            gezi_bitis.isEmpty() -> _toastMessage.value = "Bitiş yeri boş olamaz."
            gezi_saat.isEmpty() -> _toastMessage.value = "Saat bilgisi boş olamaz."
            gezi_tarih.isEmpty() -> _toastMessage.value = "Tarih bilgisi boş olamaz."
            gezi_ucret.isEmpty() -> _toastMessage.value = "Ücret bilgisi boş olamaz."
            gezi_bilgi.isEmpty() -> _toastMessage.value = "Bilgi alanı boş olamaz."
            gezi_iletisim.isEmpty() -> _toastMessage.value = "İletişim bilgisi boş olamaz."
            gezi_kontenjan.isEmpty() -> _toastMessage.value = "KOntenjan bilgisi boş olamaz."
            else -> grepo.Kaydet(
                gezi_basla,
                gezi_bitis,
                gezi_saat,
                gezi_tarih,
                gezi_ucret,
                gezi_bilgi,
                gezi_iletisim,
                gezi_kontenjan

            )
        }
    }
}