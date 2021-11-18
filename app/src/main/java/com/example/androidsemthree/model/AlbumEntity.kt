package com.example.androidsemthree.model

data class AlbumEntity(
    var id: Int,
    var title: String,
    var description: String,
    var url: String? = "",
    var list: List<String>? = ArrayList()
)
