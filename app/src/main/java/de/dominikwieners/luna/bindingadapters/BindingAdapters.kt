package de.dominikwieners.luna.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@SuppressWarnings("unused")
class BindingAdapters{
    @BindingAdapter("imageUrl")
    fun loadImage(view:ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}