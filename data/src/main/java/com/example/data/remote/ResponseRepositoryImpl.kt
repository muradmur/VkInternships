package com.example.data.remote

import com.example.domain.model.GifResponse
import com.example.domain.repository.ResponseRepository
import dagger.Binds
import io.reactivex.Single
import javax.inject.Inject

class ResponseRepositoryImpl @Inject constructor (
    private val api: Api,
) : ResponseRepository {

    override fun getGifByPhrase(apiKey: String, searchPhrase: String, limit: Int): Single<GifResponse> =
        api.getGifByPhrase(apiKey, searchPhrase, limit).map {
            GifResponse(
                data = it.data,
                pagination = it.pagination
            )
        }
}