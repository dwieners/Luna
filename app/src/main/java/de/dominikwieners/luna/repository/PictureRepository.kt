package de.dominikwieners.luna.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object PictureRepository {


    class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            val orignal = chain!!.request()
            val request = orignal.newBuilder()
                    .addHeader("Authorization", "Client-ID dc29317f7f602feefccda9b239bb519dd91f76c51efd186cf3d944771acc7658")
                    .method(orignal.method(), orignal.body())
                    .build()
            return chain.proceed(request)
        }
    }


    fun create(): UnsplashService {
        val client = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(UnsplashService::class.java)
    }

}