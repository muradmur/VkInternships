package com.example.webanttrainee.presentation.ui.fragments.new_

import com.example.domain.usecases.GetPictureUseCase
import com.example.webanttrainee.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    getPictureUseCase: GetPictureUseCase
) : BaseViewModel(getPictureUseCase, true) {

    init {
        getImages()
    }
}