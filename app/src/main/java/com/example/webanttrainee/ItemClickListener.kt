package com.example.webanttrainee

fun interface ItemClickListener<T> {
    fun onClick(value: T)
}