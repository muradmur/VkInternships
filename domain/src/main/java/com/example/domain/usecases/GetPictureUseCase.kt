package com.example.domain.usecases

import com.example.domain.model.ImageResponse
import com.example.domain.repository.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPictureUseCase @Inject constructor(
    private val responseRepository: ResponseRepository
) {

    fun execute(isNew: Boolean, page: Int, limit: Int): Single<ImageResponse> {
        return responseRepository.getPicture(isNew, page, limit)
    }
}