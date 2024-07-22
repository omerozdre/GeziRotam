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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.databinding.FragmentGeziOlusturBinding
import com.example.tasarimcalismasi_ii.ui.viewmodel.GeziOlusturViewModel
import com.example.tasarimcalismasi_ii.ui.viewmodel.GirisViewModel
import com.example.tasarimcalismasi_ii.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeziOlusturFragment : Fragment() {
    private lateinit var binding: FragmentGeziOlusturBinding
    private lateinit var viewModel: GeziOlusturViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_gezi_olustur, container, false)
        binding.geziOlusturFragment = this

        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geciciViewModel : GeziOlusturViewModel by viewModels()
        viewModel = geciciViewModel //ViewModel ile baglantıyı sagladık
    }



    fun geziOlusturButtonKaydet(gezi_basla : String, gezi_bitis : String, gezi_saat  : String, gezi_tarih : String,
                                gezi_ucret : String ,gezi_bilgi : String,gezi_iletisim : String,gezi_kontenjan : String){
        viewModel.Kaydet(gezi_basla,gezi_bitis,gezi_saat,gezi_tarih,gezi_ucret,gezi_bilgi,gezi_iletisim,gezi_kontenjan)
    }

    fun fabGeziOlusturTikla(it:View){
        Navigation.gecisYap(it,R.id.olusturdanGecisAnaSayfa)
    }

}