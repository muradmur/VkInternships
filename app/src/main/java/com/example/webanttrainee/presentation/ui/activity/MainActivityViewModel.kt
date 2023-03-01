package com.example.webanttrainee.presentation.ui.activity

import com.example.domain.usecases.GetPictureUseCase
import com.example.webanttrainee.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel@Inject constructor(
    getPictureUseCase: GetPictureUseCase
) : BaseViewModel(getPictureUseCase) {

}