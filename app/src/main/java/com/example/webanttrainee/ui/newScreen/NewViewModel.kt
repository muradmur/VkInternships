package com.example.webanttrainee.ui.newScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webanttrainee.model.Data
import com.example.webanttrainee.model.PictureList
import com.example.webanttrainee.remote.PictureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class NewViewModel(
    private val pictureRepository: PictureRepository
) : ViewModel() {

    private val _pictureList = MutableLiveData<List<Data>>()
    var pictureList = _pictureList

    //одну лайвдату дата, содержит все стейты | можно через Sealed класс,
    // который будет содержать 2 дата классы лайвдаты вместе со стейтами
    private val _isVisible = MutableLiveData<Boolean>()
    var isVisible = _isVisible

    private val _isRefreshing = MutableLiveData<Boolean>()
    var isRefreshing = _isRefreshing

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading = _isLoading

    private val _totalPage = MutableLiveData<Int>()
    var totalPage = _totalPage

    fun getImages(isNew: Boolean, page: Int, limit: Int) {
        isLoading.value = true
        isVisible.value = true
        val compositeDisposable = CompositeDisposable()
            pictureRepository.getPicture(isNew, page, limit)
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse(it)
                }, {
                    onFailure()
                }).let(compositeDisposable::add)
    }

    private fun onFailure() {

        isVisible.value = false
        isLoading.value = false
        isRefreshing.value = false
    }

    private fun onResponse(it: PictureList) {

        pictureList.value = it.result
        totalPage.value = it.countOfPages
        isLoading.value = false
        isVisible.value = false
        isRefreshing.value = false
    }
}