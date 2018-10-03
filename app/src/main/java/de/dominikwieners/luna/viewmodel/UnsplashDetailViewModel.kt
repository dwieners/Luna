package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.dominikwieners.luna.model.UnsplashPictureResponse
import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.graphics.Color
import android.widget.ImageView


class UnsplashDetailViewModel:ViewModel(){

    var detail = MutableLiveData<UnsplashPictureResponse>()

    fun init(unsplashPictureResponse: UnsplashPictureResponse){
        this.detail.value = unsplashPictureResponse
    }

    fun createdAt():String {
        var time: String = detail.value!!.created_at!!
        println(time)
        return time.substring(0, 10)
    }

    fun location():String{
        var str:String? = detail.value!!.user!!.location
        str?.let {
            return str
        }
        return "---"
    }

    fun likes():String{
        var str = detail.value!!.likes.toString()
        return str
    }

    fun color():Int{
        var color = detail.value!!.color!!
        return Color.parseColor(color)
    }



}