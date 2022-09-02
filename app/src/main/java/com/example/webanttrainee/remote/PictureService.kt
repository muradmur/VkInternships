package com.example.webanttrainee.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.webanttrainee.model.PictureResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureService {
    @GET("/api/photos")
    fun getPicture(
        @Query("new") isNew: Boolean,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Single<PictureResponse>

    companion object {

        const val BASE_URL = "https://gallery.prod1.webant.ru"
        var pictureApi: PictureService? = null
        fun getInstance(context: Context): PictureService {

            val httpLoginInterceptor = HttpLoggingInterceptor().also {
                HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoginInterceptor)
                .addInterceptor(ChuckerInterceptor.Builder(context).build())
                .build()

            return if (pictureApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

                retrofit.create(PictureService::class.java)
            } else pictureApi!!
        }
    }
}
