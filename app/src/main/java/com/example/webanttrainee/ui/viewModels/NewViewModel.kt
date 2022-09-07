package com.example.webanttrainee.ui.viewModels

import com.example.webanttrainee.remote.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    pictureRepository: PictureRepository
) : BaseViewModel(pictureRepository) {

    init {
        getImages(true)
    }

}