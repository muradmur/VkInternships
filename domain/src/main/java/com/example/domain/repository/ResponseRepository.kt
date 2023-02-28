package com.example.domain.repository

import com.example.domain.model.GifResponse
import io.reactivex.Single

interface ResponseRepository {

    fun getGifByPhrase (apiKey: String, searchPhrase: String, limit: Int): Single<GifResponse>
}