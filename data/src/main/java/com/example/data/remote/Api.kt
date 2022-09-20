package com.example.data.remote

import com.example.domain.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api/photos")
    fun getPicture(
        @Query("new") isNew: Boolean,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Single<ImageResponse>

    companion object {
        const val BASE_URL = "https://gallery.prod1.webant.ru"
        const val LIMIT = 24
    }
}
