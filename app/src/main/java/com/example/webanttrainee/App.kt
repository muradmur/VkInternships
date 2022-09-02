package com.example.webanttrainee

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.webanttrainee.remote.PictureService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var pictureApi: PictureService

    override fun onCreate() {
        super.onCreate()
        configureRetrofit()
    }

    private fun configureRetrofit(){
        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder(applicationContext).build())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        pictureApi = retrofit.create(PictureService::class.java)
    }

    companion object{
        const val ARG_DATA = "ARG_DATA"
        const val BASE_URL = "https://gallery.prod1.webant.ru"
    }
}