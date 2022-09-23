package com.example.domain.model

data class ImageResponse(
    val countOfPages: Int,
    val data: List<Data>,
    val itemsPerPage: Int,
    val totalItems: Int
)