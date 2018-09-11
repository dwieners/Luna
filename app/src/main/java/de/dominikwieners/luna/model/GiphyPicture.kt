package de.dominikwieners.luna.model


data class GiphyPictureResponse(
        val data: List<GiphyData>,
        val pagination: GiphyPagination
)

data class GiphyPagination(
        val total_count: Int,
        val count: Int,
        val offset: Int
)

data class GiphyData(
        val type: String,
        val id: String,
        val user: GiphyUser,
        val images: GiphyImages
)

data class GiphyUser(
        val avatar_url: String,
        val banner_url: String,
        val banner_image: String,
        val profile_url: String,
        val username: String,
        val display_name: String
)

data class GiphyImages(
        val fixed_width_small: GiphyFixedWidthSmall
)

data class GiphyFixedWidthSmall(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val mp4: String,
        val mp4_size: String,
        val webp: String,
        val webp_size: String
)