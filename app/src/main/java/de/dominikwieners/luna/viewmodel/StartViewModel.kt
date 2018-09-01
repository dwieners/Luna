package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.repository.PictureRepository
import de.dominikwieners.luna.repository.UnsplashService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class StartViewModel : ViewModel(){

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


    var resultdata = MutableLiveData<List<UnsplashPictureResponse>>()
    var isError  = MutableLiveData<Boolean>()

    var isLoading:ObservableField<Boolean> = ObservableField()

    val client by lazy {
        PictureRepository.create()
    }

    fun fetchPictures() {

        client.getPictures(1, 5, UnsplashService.Order.ORDER_BY_OLDEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.set(true) }
                .doOnTerminate { isLoading.set(false) }
                .subscribe(
                        { result ->  resultdata.value = result},
                        { error ->   isError.value = true})

    }

}


