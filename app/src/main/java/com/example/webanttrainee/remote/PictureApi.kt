package com.example.webanttrainee.remote

import com.example.webanttrainee.model.PictureList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET("/api/photos")
    fun getPicture(@Query("new") pictureStatus: Boolean): Single<PictureList>
}