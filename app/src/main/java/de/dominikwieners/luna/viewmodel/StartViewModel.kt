package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.ViewModel
import de.dominikwieners.luna.model.Picture

class StartViewModel : ViewModel(){

    var pictures:ArrayList<Picture> = ArrayList<Picture>()

    init{
        pictures.add(Picture("Dominik"))
        pictures.add(Picture("Wieners"))
    }

    fun getIndexZero():String{
        return "${pictures[0].name} ${pictures[1].name}"
    }
}