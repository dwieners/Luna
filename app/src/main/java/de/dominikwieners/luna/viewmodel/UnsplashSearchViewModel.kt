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
    var isLoading = MutableLiveData<Boolean>()

    var nextData = MutableLiveData<UnsplashPictureSearchResponse>()
    var isNextError = MutableLiveData<Boolean>()
    var isNextLoading = MutableLiveData<Boolean>()

    val client by lazy {
        UnsplashRepository.create()
    }


    @SuppressLint("CheckResult")
    fun fetchUnsplashSearchResult(query:String, page:Int, per_page:Int) {
        client.searchPhotos(query,page,per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isLoading.value = true
                    println("Loading")
                }
                .doOnTerminate {
                    isLoading.value = false
                    println("Finish")
                }
                .subscribe(
                        { result -> resultData.value = result},
                        { error ->   isError.value = true})

    }


    @SuppressLint("CheckResult")
    fun fetchNextUnsplshSearchResult(query: String, page: Int, per_page: Int){
        client.searchPhotos(query, page, per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isNextLoading.value = true
                    println("LoadingNext")
                }
                .doOnTerminate {
                    isNextLoading.value = false
                    println("FinishNext")
                }
                .subscribe(
                        { result ->  nextData.value = result},
                        { error ->   isNextError.value = true})
    }


}