package com.example.webanttrainee.model

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    val countOfPages: Int,
    @SerializedName("data")
    val images: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
)