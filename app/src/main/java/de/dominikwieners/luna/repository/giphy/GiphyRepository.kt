package de.dominikwieners.luna.repository.giphy

import de.dominikwieners.luna.repository.unsplash.UnsplashRepository
import de.dominikwieners.luna.repository.unsplash.UnsplashService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object GiphyRepository {

    fun create(): GiphyService {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.giphy.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return retrofit.create(GiphyService::class.java)
    }
}