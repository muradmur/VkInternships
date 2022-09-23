package com.example.data.remote

import com.example.domain.model.ImageResponse
import com.example.domain.repository.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class ResponseRepositoryImpl @Inject constructor(
    private val api: Api,
) : ResponseRepository {

    override fun getPicture(isNew: Boolean, page: Int, limit: Int): Single<ImageResponse> =
        api.getPicture(isNew, page, limit).map {
            ImageResponse(
                countOfPages = it.countOfPages,
                data = it.images,
                itemsPerPage = it.itemsPerPage,
                totalItems = it.totalItems,
            )
        }
}