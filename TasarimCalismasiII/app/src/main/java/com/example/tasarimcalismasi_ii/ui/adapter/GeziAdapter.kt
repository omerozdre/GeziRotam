package com.example.tasarimcalismasi_ii.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tasarimcalismasi_ii.R
import com.example.tasarimcalismasi_ii.data.entity.Geziler
import com.example.tasarimcalismasi_ii.databinding.CardTasarimGeziBinding
import com.example.tasarimcalismasi_ii.databinding.FragmentAnaSayfaBinding
import com.example.tasarimcalismasi_ii.ui.fragment.AnaSayfaFragment
import com.example.tasarimcalismasi_ii.ui.fragment.AnaSayfaFragmentDirections
import com.example.tasarimcalismasi_ii.ui.viewmodel.AnaSayfaViewModel
import com.google.android.material.snackbar.Snackbar

//adapter, kart üzeirnde yapacağımız işlemleri kontrol etmemizi sağlar
//context fragment iceirsinde oldugumuzu belirttigimiz bir parça.
class GeziAdapter(var mContext: Context,var gezilerListesi:List<Geziler>,var viewModel:AnaSayfaViewModel)
    : RecyclerView.Adapter<GeziAdapter.CardTasarimGeziTutucu>() {

    //inner class cardtasarımgezi yi temsil ediyor
    inner class CardTasarimGeziTutucu(var geziTasarim:CardTasarimGeziBinding):RecyclerView.ViewHolder(geziTasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimGeziTutucu {
        val binding : CardTasarimGeziBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_tasarim_gezi , parent,
            false)
        return CardTasarimGeziTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimGeziTutucu, position: Int) {//card ile ilgili işlemleri burada yapacağız
        val gezi = gezilerListesi.get(position)// position döngü gibi calısıyor liste için
        val t = holder.geziTasarim//holder sınıfı sayesinde CardTasarimGeziTutucu a CardTasarimGeziTutucu sayesinde de CardTasarimGeziBinding a erişşiyoruz

        t.geziNesnesi = gezi

        t.ivSil.setOnClickListener {
            Snackbar.make(it,"${gezi.gezi_basla}-${gezi.gezi_bitis} silinsin mi?",Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    viewModel.geziSil(gezi.gezi_id!!)
                }
                .show()
        }

        t.btnKontenjan.setOnClickListener {
            if (gezi.gezi_kontenjan?.toInt() ?: 0 > 0) {
                gezi.updateKontenjan()
                gezi.updateUcret()
                viewModel.geziGuncelle(gezi)
            } else {
                Snackbar.make(it, "Kontenjan dolmuştur.", Snackbar.LENGTH_SHORT).show()
            }
        }
        t.btnkontenjaniptal.setOnClickListener {
            gezi.revertChanges()
            viewModel.geziGuncelle(gezi)
            Snackbar.make(it, "Başvuru iptal edildi.", Snackbar.LENGTH_SHORT).show()
        }
/*
        t.btnkontenjaniptal.setOnClickListener {
            gezi.revertChanges()
            gezi.updateUcret()
            viewModel.geziGuncelle(gezi)
            Snackbar.make(it, "Başvuru iptal edildi.", Snackbar.LENGTH_SHORT).show()
        }
*/
    }
    override fun getItemCount(): Int {

        return gezilerListesi.size
    }

}