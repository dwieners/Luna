package de.dominikwieners.luna.repository.giphy

import de.dominikwieners.luna.model.GiphyPictureResponse
import de.dominikwieners.luna.model.UnsplashPictureResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    object Key {
        val API_KEY = "GsZ5m6MLBioS9krfE5FpZ0mW93nNVFdi"
    }

    @GET("/v1/gifs/trending")
    fun getPictures(@Query("api_key") api_key: String,
                    @Query("limit") limit: Int,
                    @Query("offset") offset: Int)
            : Observable<List<GiphyPictureResponse>>
}