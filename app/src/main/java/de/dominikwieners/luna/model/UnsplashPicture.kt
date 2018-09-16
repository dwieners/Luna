package de.dominikwieners.luna.model

import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import com.squareup.picasso.Picasso


// Model (data provider)
//--------------------------------
// Retrofit
// SQLite db
// Realm db
// Firebase

data class UnsplashPictureResponse(
        val id: String?,
        val created_at: String?,
        val updated_at: String?,
        val width: Int?,
        val heigth: Int?,
        val color: String?,
        val description: String?,
        val urls : UnsplashUrlsResponse?,
        val links : UnsplashLinksResponse?,
        val sponsored: Boolean?,
        val likes: Int?,
        val liked_by_user: Boolean?,
        val user: UnsplashUserResponse?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(UnsplashUrlsResponse::class.java.classLoader),
            parcel.readParcelable(UnsplashLinksResponse::class.java.classLoader),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readParcelable(UnsplashUserResponse::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeValue(width)
        parcel.writeValue(heigth)
        parcel.writeString(color)
        parcel.writeString(description)
        parcel.writeParcelable(urls, flags)
        parcel.writeParcelable(links, flags)
        parcel.writeValue(sponsored)
        parcel.writeValue(likes)
        parcel.writeValue(liked_by_user)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashPictureResponse> {
        override fun createFromParcel(parcel: Parcel): UnsplashPictureResponse {
            return UnsplashPictureResponse(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashPictureResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnsplashUrlsResponse(
        val raw : String?,
        val full : String?,
        val regular : String?,
        val small : String?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raw)
        parcel.writeString(full)
        parcel.writeString(regular)
        parcel.writeString(small)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashUrlsResponse> {
        override fun createFromParcel(parcel: Parcel): UnsplashUrlsResponse {
            return UnsplashUrlsResponse(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashUrlsResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnsplashLinksResponse(
        val self : String?,
        val html : String?,
        val download : String?,
        val download_location : String?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(download)
        parcel.writeString(download_location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashLinksResponse> {
        override fun createFromParcel(parcel: Parcel): UnsplashLinksResponse {
            return UnsplashLinksResponse(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashLinksResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnsplashUserResponse(
        val id : String?,
        val updated_at : String?,
        val username : String?,
        val first_name : String?,
        val last_name : String?,
        val twitter_username : String?,
        val portfolio_url : String?,
        val bio : String?,
        val location : String?,
        val links : UnsplashUserLinksResponse?,
        val profile_image : UnsplashUserProfileImage?,
        val instagram_username : String?,
        val total_collections : Int?,
        val total_likes : Int?,
        val total_photos : Int?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(UnsplashUserLinksResponse::class.java.classLoader),
            parcel.readParcelable(UnsplashUserProfileImage::class.java.classLoader),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(updated_at)
        parcel.writeString(username)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(twitter_username)
        parcel.writeString(portfolio_url)
        parcel.writeString(bio)
        parcel.writeString(location)
        parcel.writeParcelable(links, flags)
        parcel.writeParcelable(profile_image, flags)
        parcel.writeString(instagram_username)
        parcel.writeValue(total_collections)
        parcel.writeValue(total_likes)
        parcel.writeValue(total_photos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashUserResponse> {
        override fun createFromParcel(parcel: Parcel): UnsplashUserResponse {
            return UnsplashUserResponse(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashUserResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnsplashUserLinksResponse(
        val self : String?,
        val html : String?,
        val photos : String?,
        val likes : String?,
        val portfolio : String?,
        val following : String?,
        val followers : String?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(photos)
        parcel.writeString(likes)
        parcel.writeString(portfolio)
        parcel.writeString(following)
        parcel.writeString(followers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashUserLinksResponse> {
        override fun createFromParcel(parcel: Parcel): UnsplashUserLinksResponse {
            return UnsplashUserLinksResponse(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashUserLinksResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnsplashUserProfileImage(
        val small : String?,
        val medium : String?,
        val large : String?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(small)
        parcel.writeString(medium)
        parcel.writeString(large)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashUserProfileImage> {
        override fun createFromParcel(parcel: Parcel): UnsplashUserProfileImage {
            return UnsplashUserProfileImage(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashUserProfileImage?> {
            return arrayOfNulls(size)
        }
    }
}