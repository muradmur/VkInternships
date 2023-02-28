package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
): Parcelable