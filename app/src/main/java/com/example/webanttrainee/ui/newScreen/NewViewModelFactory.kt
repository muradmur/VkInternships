package com.example.webanttrainee.ui.newScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webanttrainee.remote.PictureRepository

class NewViewModelFactory(
    private val pictureRepository: PictureRepository,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewViewModel(pictureRepository) as T
    }
}