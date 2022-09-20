package com.example.webanttrainee.domain.usecases

import com.example.webanttrainee.model.PictureResponse
import com.example.webanttrainee.remote.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPictureUseCase @Inject constructor(
    private val responseRepository: ResponseRepository
) {

    fun execute(isNew: Boolean, page: Int, limit: Int): Single<PictureResponse> {
        return responseRepository.getPicture(isNew, page, limit)
    }
}