package com.example.domain.repository

import com.example.domain.model.ImageResponse
import io.reactivex.Single

interface ResponseRepository {

    fun getPicture(isNew: Boolean, page: Int, limit: Int): Single<ImageResponse>
}