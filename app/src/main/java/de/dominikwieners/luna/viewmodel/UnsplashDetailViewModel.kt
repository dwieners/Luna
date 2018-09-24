package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.dominikwieners.luna.model.UnsplashPictureResponse

class UnsplashDetailViewModel:ViewModel(){

    var detail = MutableLiveData<UnsplashPictureResponse>()

    fun init(unsplashPictureResponse: UnsplashPictureResponse){
        this.detail.value = unsplashPictureResponse
    }


}