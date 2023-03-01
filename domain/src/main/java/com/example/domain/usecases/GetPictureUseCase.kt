package com.example.domain.usecases

import com.example.domain.model.GifResponse
import com.example.domain.repository.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPictureUseCase @Inject constructor (
    private val responseRepository: ResponseRepository
) {

    fun execute(apiKey: String, searchPhrase: String, limit: Int, offset: Int): Single<GifResponse> {
        return responseRepository.getGifByPhrase(apiKey, searchPhrase, limit, offset)
    }
}