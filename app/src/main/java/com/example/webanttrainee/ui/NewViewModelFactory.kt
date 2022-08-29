package com.example.webanttrainee.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webanttrainee.remote.PictureRepository
import com.example.webanttrainee.remote.PictureService

class NewViewModelFactory(
    private val pictureRepository: PictureRepository,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewViewModel(pictureRepository) as T
    }
}