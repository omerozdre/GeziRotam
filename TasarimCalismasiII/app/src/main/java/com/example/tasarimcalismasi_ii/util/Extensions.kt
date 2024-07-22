package com.example.tasarimcalismasi_ii.util

import android.view.View
import androidx.navigation.Navigation

//sayfa gecislerine özgü fonkdiyon olsun


fun Navigation.gecisYap(it:View,id:Int){
    findNavController(it).navigate(id)
}
