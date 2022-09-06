package com.example.webanttrainee.remote

import javax.inject.Inject


class PictureRepository @Inject constructor(
    private val pictureApi: PictureApi,
) {
    fun getPicture(isNew: Boolean, page: Int, limit: Int) = pictureApi.getPicture(isNew, page, limit)
}