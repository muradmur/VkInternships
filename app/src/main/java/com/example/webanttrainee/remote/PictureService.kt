package com.example.webanttrainee.remote

import com.example.webanttrainee.App
import com.example.webanttrainee.model.PictureList
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
    ): Single<PictureList>

    companion object {

        const val ARG_DATA = "ARG_DATA"
        const val BASE_URL = "https://gallery.prod1.webant.ru"

        var pictureApi: PictureService? = null

        fun getInstance(): PictureService {

            val httpLoginInterceptor = HttpLoggingInterceptor().also {
                HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoginInterceptor)
                // нужен, чтобы при запуске приложения открывалась утилита с отображением запросов
//                    .addInterceptor(ChuckerInterceptor.Builder(CONTEXT).build())
                .build()

            return if (pictureApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(App.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

                retrofit.create(PictureService::class.java)
            } else pictureApi!!
        }
    }
}
