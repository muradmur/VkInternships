package com.example.webanttrainee.ui.viewModels

import com.example.webanttrainee.domain.usecases.GetPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    getPictureUseCase: GetPictureUseCase
) : BaseViewModel(getPictureUseCase) {

     init {
        getImages(false)
    }
}