package com.example.tasarimcalismasi_ii.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.data.entity.Geziler
import com.example.tasarimcalismasi_ii.databinding.FragmentAnaSayfaBinding
import com.example.tasarimcalismasi_ii.ui.adapter.GeziAdapter
import com.example.tasarimcalismasi_ii.ui.viewmodel.AnaSayfaViewModel
import com.example.tasarimcalismasi_ii.ui.viewmodel.GeziOlusturViewModel
import com.example.tasarimcalismasi_ii.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ana_sayfa, container, false)
        binding.anasayfaFragment = this

        viewModel.gezilerListesi.observe(viewLifecycleOwner){
            val gezilerAdapter = GeziAdapter(requireContext(),it,viewModel) //adapter bilgileri düzenlemek,RW nin görevi görüntülemek
            binding.geziAdapter = gezilerAdapter
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//harf girdikçe harf sildikçe sonuç getiren fonksiyon
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {//arama ikonuna bastığımızda sonuç getirir query=sorgu
                viewModel.ara(query)
                return true
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geciciViewModel : AnaSayfaViewModel by viewModels()
        viewModel = geciciViewModel //ViewModel ile baglantıyı sagladık
    }

    fun fabTikla(it:View){
        Navigation.gecisYap(it,R.id.geziOlusturGecis)
    }

    fun btnCikisTikla(it:View){
        Navigation.gecisYap(it,R.id.anasayfadanProfile)
    }

}