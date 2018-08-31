package de.dominikwieners.luna.repository

import de.dominikwieners.luna.model.UnsplashPictureResponse
import io.reactivex.Observable
import retrofit2.Call

import retrofit2.http.GET

interface UnsplashService {

    @GET("photos")
    fun getPictures(): Observable<List<UnsplashPictureResponse>>
}