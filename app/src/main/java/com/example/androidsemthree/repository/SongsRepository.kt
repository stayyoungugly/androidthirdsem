package com.example.androidsemthree.repository

import com.example.androidsemthree.R
import com.example.androidsemthree.model.Song

object SongsRepository {
    val songsList: ArrayList<Song> = arrayListOf(
        Song(0, "Smokey Tears", "May Wave$", R.drawable.tears, R.raw.tears),
        Song(1, "White Ferrari", "Frank Ocean", R.drawable.white, R.raw.white),
        Song(2, "Coldest Winter", "Kanye West", R.drawable.coldest, R.raw.coldest),
        Song(3, "Autopoetry", "Boulevard Depo", R.drawable.autopoetry, R.raw.poetry),
        Song(4, "November", "Tyler, the Creator", R.drawable.november, R.raw.november),
        Song(5, "Excuse Me", "A\$AP Rocky", R.drawable.excuse, R.raw.excuse),
    )
}
