package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val content_url: String,
    val id: String,
    val import_datetime: String,
    val rating: String,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val title: String,
    val trending_datetime: String,
    val url: String,
    val username: String,
    val images: Images,
    ): Parcelable