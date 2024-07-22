package com.example.tasarimcalismasi_ii.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasarimcalismasi_ii.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GirisViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(kullanici_email: String, kullanici_sifre: String) {
        viewModelScope.launch {
            val success = userRepository.giris(kullanici_email , kullanici_sifre)
            _loginSuccess.postValue(success)
        }
    }

}
