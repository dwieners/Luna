package de.dominikwieners.luna.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import de.dominikwieners.luna.model.GiphyPictureResponse
import de.dominikwieners.luna.repository.giphy.GiphyRepository
import de.dominikwieners.luna.repository.giphy.GiphyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GiphyViewModel: ViewModel(){

    var resultData = MutableLiveData<List<GiphyPictureResponse>>()
    var isLoading: ObservableField<Boolean> = ObservableField()
    var isError  = MutableLiveData<Boolean>()

    val client by lazy { GiphyRepository.create() }

    fun fetchGiphyImages(limit:Int, offset: Int){
        client.getPictures(GiphyService.Key.API_KEY, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.set(true) }
                .doOnTerminate { isLoading.set(false) }
                .subscribe(
                        { result ->  resultData.value = result},
                        { error ->   isError.value = true})

    }


}