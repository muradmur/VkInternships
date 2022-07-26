package com.example.webanttrainee.model

import com.google.gson.annotations.SerializedName

data class PictureList(
    val countOfPages: Int,
    @SerializedName("data")
    val result: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
)