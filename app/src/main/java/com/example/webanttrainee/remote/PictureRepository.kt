package com.example.webanttrainee.remote

class PictureRepository(
    private val pictureService: PictureService,
    private val isNew: Boolean,
    private val page: Int,
    private val limit: Int
) {
    fun getPicture() = pictureService.getPicture(isNew, page, limit)
}