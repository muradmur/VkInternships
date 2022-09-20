package com.example.webanttrainee.domain.repository

import com.example.webanttrainee.model.PictureResponse
import io.reactivex.Single

interface ResponseRepository {

    fun getPicture(isNew: Boolean, page: Int, limit: Int): Single<PictureResponse>
}