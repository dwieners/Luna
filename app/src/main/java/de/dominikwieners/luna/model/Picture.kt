package de.dominikwieners.luna.model

// Model (data provider)
//--------------------------------
// Retrofit
// SQLite db
// Realm db
// Firebase


class UnsplashPictureResponse(
        val id: String,
        val created_at: String,
        val updated_at: String,
        val width: Int,
        val heigth: Int,
        val color: String,
        val description: String,
//        val urls : List<UnsplashUrlsResponse>,
//        val links : List<UnsplashLinksResponse>,
        val sponsored: Boolean,
        val likes: Int,
        val liked_by_user: Boolean
//        val user: UnsplashUserResponse
)

/*
class UnsplashUrlsResponse(
        val raw : String,
        val full : String,
        val regular : String,
        val small : String
)

class UnsplashLinksResponse(
        val self : String,
        val html : String,
        val download : String,
        val download_location : String
)

class UnsplashUserResponse(
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

class UnsplashUserLinksResponse(
        val self : String,
        val html : String,
        val photos : String,
        val likes : String,
        val portfolio : String,
        val following : String,
        val followers : String
)

class UnsplashUserProfileImage(
        val small : String,
        val medium : String,
        val large : String
)
        */