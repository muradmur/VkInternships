package com.example.webanttrainee.domain.repository

import com.example.webanttrainee.domain.model.ImageResponse
import io.reactivex.Single

interface ResponseRepository {

    fun getPicture(isNew: Boolean, page: Int, limit: Int): Single<ImageResponse>
}