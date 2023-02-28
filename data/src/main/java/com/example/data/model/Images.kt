package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val original: Original,
) : Parcelable