package com.example.data.model

import android.os.Parcelable
import com.example.domain.model.Data
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ImageResponse(
    val countOfPages: Int,
    @SerializedName("data")
    val images: @RawValue List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
): Parcelable