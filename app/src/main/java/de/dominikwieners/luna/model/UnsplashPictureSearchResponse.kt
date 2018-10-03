package de.dominikwieners.luna.model

data class UnsplashPictureSearchResponse(
        val total:Int,
        val total_pages: Int,
        val results: List<UnsplashPictureResponse>
)