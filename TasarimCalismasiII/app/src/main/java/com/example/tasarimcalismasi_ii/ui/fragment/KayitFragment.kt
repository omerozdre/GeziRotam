package com.example.tasarimcalismasi_ii.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.databinding.FragmentKayitBinding
import com.example.tasarimcalismasi_ii.ui.viewmodel.KayitViewModel
import com.example.tasarimcalismasi_ii.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KayitFragment : Fragment() {
    private lateinit var binding: FragmentKayitBinding
    private lateinit var viewModel: KayitViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kayit,container, false)
        binding.kayitFragment=this
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnKayitOl.setOnClickListener {

            val kisi_mail = binding.etKisiKayitIsim.text.toString()
            val kisi_sifre = binding.etSifreTekrar.text.toString()
            val kisi_sifre_tekrar = binding.etSifreTekrar.text.toString()
            viewModel.register(kisi_mail, kisi_sifre, kisi_sifre_tekrar)
            //KisiKaydet(kisi_mail,kisi_sifre,kisi_sifre_tekrar)


            viewModel.registerSuccess.observe(viewLifecycleOwner, { success ->
                if (success) {
                    // Kayıt başarılı, giriş sayfasına yönlendir
                    Navigation.gecisYap(binding.root, R.id.girisGecis)
                } else {
                    // Kayıt başarısız, hata mesajı göster
                    Toast.makeText(context, "Kayıt başarısız, lütfen bilgilerinizi kontrol edin.", Toast.LENGTH_SHORT).show()
                }
            })

            // Hata mesajlarını gözlemleyin
            viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geciciViewModel : KayitViewModel by viewModels()
        viewModel = geciciViewModel //ViewModel ile baglantıyı sagladık
    }
/*
    fun KisiKaydet(kisi_mail : String, kisi_sifre : String,kisi_sifre_tekrar :String){
        Log.e("Kisi Kaydet","$kisi_mail - $kisi_sifre - $kisi_sifre_tekrar")
    }
*/
    fun fabkayitTikla(it:View){
        Navigation.gecisYap(it,R.id.girisGecis)
    }


}