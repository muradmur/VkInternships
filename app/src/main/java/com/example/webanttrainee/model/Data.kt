package com.example.webanttrainee.model

import java.io.Serializable

data class Data(
    val dateCreate: String,
    val description: String,
    val id: Int,
    val image: Image,
    val name: String,
    val new: Boolean,
    val popular: Boolean,
    val user: String
): Serializable