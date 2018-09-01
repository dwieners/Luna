package de.dominikwieners.luna.repository

import de.dominikwieners.luna.model.UnsplashPictureResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    object Order {
        val ORDER_BY_LATEST = "latest"
        val ORDER_BY_OLDEST = "oldest"
        val ORDER_BY_POPULAR = "popular"
    }

    @GET("photos")
    fun getPictures(@Query("page") page: Int,
                    @Query("per_page") per_page: Int,
                    @Query("order_by") order_by: String)
            : Observable<List<UnsplashPictureResponse>>
}