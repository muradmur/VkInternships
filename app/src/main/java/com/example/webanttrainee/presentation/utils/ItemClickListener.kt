package com.example.webanttrainee.presentation.utils

fun interface ItemClickListener<T> {
    fun onClick(value: T)
}