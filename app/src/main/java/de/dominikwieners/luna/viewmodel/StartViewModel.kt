package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.ViewModel


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

    //private val pictureListObservable:LiveData<List<UnsplashPictureResponse>>







}