package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class GifResponse(
    val data: @RawValue List<Data>,
    val pagination: Pagination
): Parcelable