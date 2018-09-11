package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.repository.unsplash.UnsplashRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UnsplashViewModel : ViewModel(){

    // ViewModel(Plain Java or BaseObservable)
    //--------------------------------
    // Exposing state (progress, offline, empty, error, etc.)
    // Exposing data
    // Handling visibility
    // Handling Extras & Arguments (Bundle)
    // Input validation
    // Executing data calls in the Model
    // Executing UI commands in the View

    // Abstraction
    //-----------------
    // Command pattern (Abstraction of event listeners)
    // Navigation pattern ( Abstraction for navigation behind screens)
    // Repository pattern ( Abstraction for some data provider)

    // Dependency injection (Easy to Mock and write Unit Tests
    //----------------------
    // Navigator
    // MyRepository
    // MyGeolocationService
    // MyBoradcastBus

    //Only use Application Context


    var resultData = MutableLiveData<List<UnsplashPictureResponse>>()
    var isError  = MutableLiveData<Boolean>()
    var isLoading:ObservableField<Boolean> = ObservableField()

    var nextData = MutableLiveData<List<UnsplashPictureResponse>>()
    var isNextError = MutableLiveData<Boolean>()
    var isNextLoading:ObservableField<Boolean> = ObservableField()

    val client by lazy {
        UnsplashRepository.create()
    }

    fun fetchUnsplashPictures(page:Int, per_page:Int, order:String) {
        client.getPictures(page, per_page, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.set(true) }
                .doOnTerminate { isLoading.set(false) }
                .subscribe(
                        { result ->  resultData.value = result},
                        { error ->   isError.value = true})

    }

    fun fetchNextUnsplshPictures(page: Int, per_page: Int, order: String){
        client.getPictures(page, per_page, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isNextLoading.set(true) }
                .doOnTerminate { isNextLoading.set(false) }
                .subscribe(
                        { result ->  nextData.value = result},
                        { error ->   isNextError.value = true})
    }



}


