package com.example.webanttrainee.model

import android.os.Parcelable
import com.example.webanttrainee.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val dateCreate: String,
    val description: String,
    val id: Int,
    val image: Image,
    val name: String,
    val new: Boolean,
    val popular: Boolean,
    val user: String
): Parcelable