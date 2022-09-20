package com.example.webanttrainee.utils

fun interface ItemClickListener<T> {
    fun onClick(value: T)
}