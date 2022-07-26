package com.example.webanttrainee.model

data class PictureList(
    val countOfPages: Int,
    val `data`: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
)