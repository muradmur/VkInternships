package com.example.webanttrainee.domain.usecases

import com.example.webanttrainee.domain.model.ImageResponse
import com.example.webanttrainee.domain.repository.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPictureUseCase @Inject constructor(
    private val responseRepository: ResponseRepository
) {

    fun execute(isNew: Boolean, page: Int, limit: Int): Single<ImageResponse> {
        return responseRepository.getPicture(isNew, page, limit)
    }
}