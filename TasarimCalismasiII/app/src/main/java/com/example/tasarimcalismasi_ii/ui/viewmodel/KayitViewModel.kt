package com.example.tasarimcalismasi_ii.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasarimcalismasi_ii.data.entity.User
import com.example.tasarimcalismasi_ii.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KayitViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
    /*
    fun register(kullanici_email: String, kullanici_sifre: String, kullanici_sifre_onay: String) {
        when {
            kullanici_email.isEmpty() || kullanici_sifre.isEmpty() || kullanici_sifre_onay.isEmpty() -> {
                _errorMessage.value = "Boş Alan Bırakmayınız"
            }
            kullanici_sifre != kullanici_sifre_onay -> {
                _errorMessage.value = "Şifreler eşleşmiyor"
            }
            kullanici_sifre.length < 6 -> {
                _errorMessage.value = "Şifre en az 6 karakter olmalıdır"
            }

            else -> {
                val user = User(kullanici_email, kullanici_sifre)
                viewModelScope.launch {

                        val success = userRepository.kayit(user)
                        if (success) {
                            _registerSuccess.postValue(true)
                        } else {
                            _errorMessage.postValue("Bu email ile kayıtlı kullanıcı zaten var")
                        }
                    }
            }
        }
    }
}*/
/*
    fun register(kullanici_email: String, kullanici_sifre: String, kullanici_sifre_onay: String) {
        if (kullanici_email.isEmpty() || kullanici_sifre.isEmpty() || kullanici_sifre_onay.isEmpty()) {
            _errorMessage.value = "Boş Alan Bırakmayınız"
            return
        }
        if (kullanici_sifre.length < 6) {
            _errorMessage.value = "Şifre en az 6 karakter olmalıdır"
            return
        }
        if (kullanici_sifre != kullanici_sifre_onay) {
            _errorMessage.value = "Şifreler eşleşmiyor"
            return
        }

        val user = User(kullanici_email, kullanici_sifre)
        viewModelScope.launch {
            val success = userRepository.kayit(user)
            if (success) {
                _registerSuccess.postValue(true)
            } else {
                // userRepository'den gelen _errorMessage LiveData'sını kullanarak hata mesajını işleyin
            }
        }
    }
}*/


    fun register(kullanici_email: String, kullanici_sifre: String, kullanici_sifre_onay: String) {
        if(kullanici_email.isEmpty() || kullanici_sifre.isEmpty() || kullanici_sifre_onay.isEmpty()){
            _errorMessage.value = "Boş Alan Bırakmayınız"
        }
        if (kullanici_sifre.length < 6) {
            _errorMessage.value = "Şifre en az 6 karakter olmalıdır"
            return
        }
        if (kullanici_sifre != kullanici_sifre_onay) {
            _errorMessage.value = "Şifreler eşleşmiyor"
            return
        }
        val user = User(kullanici_email, kullanici_sifre)
        viewModelScope.launch {
            val success = userRepository.kayit(user)
            if (success) {
                _registerSuccess.postValue(true)
            } else {
                _errorMessage.postValue("Bu email ile kayıtlı kullanıcı zaten var")
            }
        }
    }}


