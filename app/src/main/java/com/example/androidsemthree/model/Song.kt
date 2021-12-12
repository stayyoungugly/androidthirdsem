package com.example.androidsemthree.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    val id: Int,
    val title: String,
    val author: String,
    @DrawableRes
    val cover: Int,
    @RawRes
    val sound: Int
)
