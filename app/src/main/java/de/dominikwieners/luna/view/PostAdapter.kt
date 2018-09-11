package de.dominikwieners.luna.view

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.UnsplashPostItemBinding
import de.dominikwieners.luna.model.UnsplashPictureResponse
import kotlinx.android.synthetic.main.unsplash_post_item.view.*

class PostViewHolder: RecyclerView.ViewHolder{

    private val itemBinding: UnsplashPostItemBinding

    constructor(itemBinding:UnsplashPostItemBinding) : super(itemBinding.root) {
        this.itemBinding = itemBinding
    }

    fun getItemBinding(): UnsplashPostItemBinding {
        return itemBinding
    }

}

class PostAdapter(private var postList: ArrayList<UnsplashPictureResponse>) : RecyclerView.Adapter<PostViewHolder>() {

    private var isFooter = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding: UnsplashPostItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.unsplash_post_item, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var unsplashPictureResponse = postList[position]
        holder.getItemBinding().picture = unsplashPictureResponse

        //Should be done width BindingAdapters
        Picasso.get().load(unsplashPictureResponse.urls.small).into(holder.getItemBinding().root.iv_unsplash)
    }


    fun add(unsplashPictureResponse: UnsplashPictureResponse){
        postList.add(unsplashPictureResponse)
        notifyItemInserted(postList.size - 1)
    }

    fun addAll(list:ArrayList<UnsplashPictureResponse>){
        for(picture in list){
            add(picture)
            print( picture.user.username + ", " )
        }
        println("Adapter List: " + postList.size)
    }


}