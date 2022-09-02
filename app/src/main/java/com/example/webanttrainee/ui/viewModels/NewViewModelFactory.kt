package com.example.webanttrainee.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webanttrainee.remote.PictureRepository

class NewViewModelFactory(
    private val pictureRepository: PictureRepository,
    private val isNew: Boolean
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewViewModel(pictureRepository, isNew) as T
    }
}