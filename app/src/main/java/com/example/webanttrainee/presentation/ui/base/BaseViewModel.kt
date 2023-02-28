package com.example.webanttrainee.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.Data
import com.example.data.remote.Api
import com.example.domain.usecases.GetPictureUseCase
import com.example.webanttrainee.presentation.ui.mapper.mapDataToUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.Collections.addAll

abstract class BaseViewModel(
    private val getPictureUseCase: GetPictureUseCase,
) : ViewModel() {

    private val _pictureList = MutableLiveData<List<Data>>(listOf())
    var pictureList: LiveData<List<Data>> = _pictureList

    private val _isRefreshing = MutableLiveData(false)
    var isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _isLoading = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData(false)
    var isError: LiveData<Boolean> = _isError

    private var currentPage = 1
    private var totalItemCount: Int = 2
    private val compositeDisposable = CompositeDisposable()

    fun getImages() {
        if (!isLoading.value!! && (pictureList.value?.size ?: 0) < totalItemCount) {
            getPictureUseCase.execute(Api.API_KEY, searchPhrase = "american psycho", Api.LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isLoading.value = true }
                .doFinally { _isLoading.value = false }
                .subscribe({
                    onResponse(mapDataToUi(it.data))
                    totalItemCount = it.pagination.total_count
                    currentPage++
                }, {
                    onFailure()
                }).let(compositeDisposable::add)
        }
    }

    private fun onFailure() {
        _isRefreshing.value = false
        _isError.value = true
    }

    private fun onResponse(result: List<Data>) {
        _pictureList.postValue(pictureList.value?.toMutableList()?.apply { addAll(result) })
        _isError.value = false
        _isRefreshing.value = false
    }

    fun refresh() {
        currentPage = 1
        _pictureList.postValue(arrayListOf())
        getImages()
    }
}