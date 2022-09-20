package com.example.webanttrainee.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: Int,
    val name: String
): Parcelable