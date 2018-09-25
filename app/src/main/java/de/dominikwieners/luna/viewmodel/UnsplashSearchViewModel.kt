package de.dominikwieners.luna.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import de.dominikwieners.luna.model.UnsplashPictureSearchResponse
import de.dominikwieners.luna.repository.unsplash.UnsplashRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UnsplashSearchViewModel : ViewModel(){

    var resultData = MutableLiveData<UnsplashPictureSearchResponse>()
    var isError  = MutableLiveData<Boolean>()
    var isLoading: ObservableField<Boolean> = ObservableField()

    var nextData = MutableLiveData<UnsplashPictureSearchResponse>()
    var isNextError = MutableLiveData<Boolean>()
    var isNextLoading: ObservableField<Boolean> = ObservableField()

    val client by lazy {
        UnsplashRepository.create()
    }


    fun fetchUnsplashSearchResult(query:String, page:Int, per_page:Int) {
        client.searchPhotos(query,page,per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.set(true) }
                .doOnTerminate { isLoading.set(false) }
                .subscribe(
                        { result -> resultData.value = result},
                        { error ->   isError.value = true})

    }


    fun fetchNextUnsplshSearchResult(query: String, page: Int, per_page: Int){
        client.searchPhotos(query, per_page, per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isNextLoading.set(true) }
                .doOnTerminate { isNextLoading.set(false) }
                .subscribe(
                        { result ->  nextData.value = result},
                        { error ->   isNextError.value = true})
    }


}