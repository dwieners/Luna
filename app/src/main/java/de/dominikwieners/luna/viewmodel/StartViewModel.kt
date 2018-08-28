package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import de.dominikwieners.luna.repository.PictureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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


    val client by lazy {
        PictureRepository.create()
    }

    var disposable: Disposable? = null


    fun fetchPictures() {
        disposable = client.getPictures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.v("Picture", result[0].id) },
                        { error -> Log.e("ERROR", error.message) })
    }











}