package com.example.webanttrainee.ui.viewModels

import com.example.webanttrainee.domain.usecases.GetPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    /*pictureRepository: PictureRepository*/
    getPictureUseCase: GetPictureUseCase
) : BaseViewModel(getPictureUseCase) {

    init {
        getImages(true)
    }

}