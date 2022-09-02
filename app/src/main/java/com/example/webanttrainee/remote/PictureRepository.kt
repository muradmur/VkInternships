package com.example.webanttrainee.remote

class PictureRepository(
    private val pictureService: PictureService,
) {
    fun getPicture(isNew: Boolean, page: Int, limit: Int) = pictureService.getPicture(isNew, page, limit)
}