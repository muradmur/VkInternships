package com.example.webanttrainee.data.remote

import com.example.webanttrainee.model.PictureResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api/photos")
    fun getPicture(
        @Query("new") isNew: Boolean,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Single<PictureResponse>

    companion object {
        const val BASE_URL = "https://gallery.prod1.webant.ru"
        const val LIMIT = 24
    }
}
