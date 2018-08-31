package de.dominikwieners.luna.model

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

// Model (data provider)
//--------------------------------
// Retrofit
// SQLite db
// Realm db
// Firebase



data class UnsplashPictureResponse(
        val id: String,
        val created_at: String,
        val updated_at: String,
        val width: Int,
        val heigth: Int,
        val color: String,
        val description: String,
        val urls : UnsplashUrlsResponse,
        val links : UnsplashLinksResponse,
        val sponsored: Boolean,
        val likes: Int,
        val liked_by_user: Boolean,
        val user: UnsplashUserResponse
)

data class UnsplashUrlsResponse(
        val raw : String,
        val full : String,
        val regular : String,
        val small : String
)

data class UnsplashLinksResponse(
        val self : String,
        val html : String,
        val download : String,
        val download_location : String
)

data class UnsplashUserResponse(
        val id : String,
        val updated_at : String,
        val username : String,
        val first_name : String,
        val last_name : String,
        val twitter_username : String,
        val portfolio_url : String,
        val bio : String,
        val location : String,
        val links : UnsplashUserLinksResponse,
        val profile_image : UnsplashUserProfileImage,
        val instagram_username : String,
        val total_collections : Int,
        val total_likes : Int,
        val total_photos : Int
)

data class UnsplashUserLinksResponse(
        val self : String,
        val html : String,
        val photos : String,
        val likes : String,
        val portfolio : String,
        val following : String,
        val followers : String
)

data class UnsplashUserProfileImage(
        val small : String,
        val medium : String,
        val large : String
)
