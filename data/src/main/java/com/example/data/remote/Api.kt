package com.example.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/gifs/search")
    fun getGifByPhrase(
        @Query("api_key") apiKey: String,
        @Query("q") searchPhrase: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Single<com.example.domain.model.GifResponse>

    companion object {
        const val API_KEY = "FNeFuPJ45g27bx3Re6jzPZDHkZgmr6r5"
        const val BASE_URL = "https://api.giphy.com"
        const val LIMIT = 25
    }
}
