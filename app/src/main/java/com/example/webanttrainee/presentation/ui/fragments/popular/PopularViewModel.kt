package com.example.webanttrainee.presentation.ui.fragments.popular

import com.example.domain.usecases.GetPictureUseCase
import com.example.webanttrainee.presentation.ui.base.BaseViewModel
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