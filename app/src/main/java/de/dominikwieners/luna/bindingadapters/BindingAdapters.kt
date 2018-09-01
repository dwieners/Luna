package de.dominikwieners.luna.bindingadapters

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BindingAdapters{

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }

    /*
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = (if (value) View.VISIBLE else View.GONE)
    }
    */


}