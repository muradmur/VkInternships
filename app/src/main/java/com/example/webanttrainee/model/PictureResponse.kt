package com.example.webanttrainee.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureResponse(
    val countOfPages: Int,
    @SerializedName("data")
    val images: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
): Parcelable