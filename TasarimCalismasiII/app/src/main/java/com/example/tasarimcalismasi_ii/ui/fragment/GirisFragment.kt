package com.example.tasarimcalismasi_ii.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.databinding.FragmentGirisBinding
import com.example.tasarimcalismasi_ii.ui.viewmodel.GirisViewModel
import com.example.tasarimcalismasi_ii.ui.viewmodel.KayitViewModel
import com.example.tasarimcalismasi_ii.ui.viewmodel.ProfilViewModel
import com.example.tasarimcalismasi_ii.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GirisFragment : Fragment() {
    private lateinit var binding: FragmentGirisBinding
    private lateinit var viewModel: GirisViewModel
    private lateinit var profilViewModel: ProfilViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_giris, container, false)
        binding.girisFragment = this
        binding.lifecycleOwner = viewLifecycleOwner
/*
        viewModel = ViewModelProvider(this).get(GirisViewModel::class.java)
        profilViewModel = ViewModelProvider(requireActivity()).get(ProfilViewModel::class.java)
*/

        viewModel.loginSuccess.observe(viewLifecycleOwner, { success ->
            if (success) {
                // Giriş başarılı, ana sayfaya yönlendir
            Navigation.gecisYap(binding.root, R.id.anaSayfaFragment)
            } else {
                // Giriş başarısız, hata mesajı göster
                Toast.makeText(context, "Giriş başarısız, lütfen bilgilerinizi kontrol edin.", Toast.LENGTH_SHORT)
                    .show()
            }
        })



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geciciViewModel: GirisViewModel by viewModels()
        viewModel = geciciViewModel //ViewModel ile baglantıyı sagladık


    }

    fun twKayitOlTikla(it: View) {
        Navigation.gecisYap(it, R.id.kayitGecis)
    }

    // Buton tıklama işlevleri
    fun btnOturumAcTikla(it: View) {
        val email = binding.etKisiGirisName.text.toString()
        val password = binding.etKisiGirisSifre.text.toString()
        viewModel.login(email, password)
        //Navigation.gecisYap(it,R.id.anaSayfaFragment)
        val sharedPreferences = context?.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("email", email)
        editor?.putString("password", password)
        editor?.apply()
    }
}

/*
    fun btnOturumAcTikla(it:View){
        Navigation.gecisYap(it,R.id.anaSayfaFragment)
    }*/


