package com.example.tasarimcalismasi_ii.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.databinding.FragmentProfilBinding
import com.example.tasarimcalismasi_ii.ui.viewmodel.KayitViewModel
import com.example.tasarimcalismasi_ii.ui.viewmodel.ProfilViewModel
import com.example.tasarimcalismasi_ii.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var viewModel: ProfilViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profil, container, false)
        binding.profilFragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        val sharedPreferences = context?.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")
        binding.profilEmail.text = email
        binding.profilSifre.text = password
/*
        viewModel = ViewModelProvider(requireActivity()).get(ProfilViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.kullaniciEmail.observe(viewLifecycleOwner, { kullanici_email ->
            binding.profilEmail.text = kullanici_email.toString()
        })

        viewModel.kullaniciSifre.observe(viewLifecycleOwner, { kullanici_sifre ->
            binding.profilSifre.text = kullanici_sifre.toString()
        })*/

        /*
                binding.fabProfil.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.profildenAnasayfaya)
                }
                binding.btnProfilCikisYap.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.profildenGecise)
                }
                */
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProfilViewModel::class.java)
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geciciViewModel : ProfilViewModel by viewModels()
        viewModel = geciciViewModel //ViewModel ile baglantıyı sagladık
    }*/



    fun fabProfilTikla(it:View){
        Navigation.gecisYap(it,R.id.profildenAnasayfaya)
    }

    fun btnProfilCikisYapTikla(it:View){
        Navigation.gecisYap(it,R.id.profildenGirise)
    }
}