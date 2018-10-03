package de.dominikwieners.luna.view

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import de.dominikwieners.luna.Navigator
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.UnsplashPostItemBinding
import de.dominikwieners.luna.model.UnsplashPictureResponse
import kotlinx.android.synthetic.main.unsplash_post_item.view.*

class UnsplashViewHolder: RecyclerView.ViewHolder{

    private val itemBinding: UnsplashPostItemBinding

    constructor(itemBinding:UnsplashPostItemBinding) : super(itemBinding.root) {
        this.itemBinding = itemBinding
    }

    fun getItemBinding(): UnsplashPostItemBinding {
        return itemBinding
    }

}

class UnsplashPostAdapter(private var postList: ArrayList<UnsplashPictureResponse>, private  var context: Context, private var navigator: Navigator) : RecyclerView.Adapter<UnsplashViewHolder>() {

    private var isFooter = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        var binding: UnsplashPostItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.unsplash_post_item, parent, false)
        return UnsplashViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        var unsplashPictureResponse = postList[position]

        holder.getItemBinding().picture = unsplashPictureResponse
        Picasso.get().load(unsplashPictureResponse.urls?.small).into(holder.getItemBinding().root.iv_unsplash)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println(unsplashPictureResponse.color)
                navigator.showUnsplashDetailActivity(getActivity(), unsplashPictureResponse, false)
            }
        })
    }


    fun getActivity(): Activity {
        return context as Activity
    }


    fun add(unsplashPictureResponse: UnsplashPictureResponse) {
        postList.add(unsplashPictureResponse)
        notifyItemInserted(postList.size - 1)
    }


    fun addAll(list: ArrayList<UnsplashPictureResponse>) {
        for (picture in list) {
            add(picture)
        }
    }

}