package com.example.webanttrainee.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.Data
import com.example.data.model.Image
import com.example.data.remote.Api
import com.example.domain.usecases.GetPictureUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel(
    private val getPictureUseCase: GetPictureUseCase,
    private val isNew: Boolean
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
            getPictureUseCase.execute(this.isNew, currentPage, Api.LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isLoading.value = true }
                .doFinally { _isLoading.value = false }
                .subscribe({
                    onResponse(mapperData(it.data))
                    totalItemCount = it.totalItems
                    currentPage++
                }, {
                    onFailure()
                }).let(compositeDisposable::add)
        }
    }

    // TODO: Написать нормальный маппер
    fun mapperData(listDomain: List<com.example.domain.model.Data>): List<Data> {
        return listDomain.map {
            Data(
                dateCreate = it.dateCreate,
                description = it.description,
                id = it.id,
                image = Image(
                    id = it.image.id,
                    name = it.image.name,
                ),
                name = it.name,
                new = it.new,
                popular = it.popular,
                user = if (it.user != null) it.user else ""
            )
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