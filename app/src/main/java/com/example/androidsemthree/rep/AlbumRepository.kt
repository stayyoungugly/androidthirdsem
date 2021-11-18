package com.example.androidsemthree.rep

import com.example.androidsemthree.model.AlbumEntity

object AlbumRepository {
    private var albums = ArrayList<AlbumEntity>()
    private var auto_id = 0
    private var auto_url =
        "https://w7.pngwing.com/pngs/72/42/png-transparent-vkontakte-social-networking-service-account-user-facebook-anonymous-mask-miscellaneous-blue-people.png"
    private var auto_list = listOf(auto_url)

    init {
        albums = arrayListOf(
            AlbumEntity(
                id = auto_id++,
                title = "College Dropout",
                description = "West started to get more and more involved in the hip-hop scene of the city and when he was 17, he wrote a rap song called ‘Green Eggs and Ham’. He persuaded his mother to pay him money so that he can start recording in the studio. Although his mother did not want this for him, she started accompanying him to a small basement studio in the city. There west met ‘The Godfather of Chicago Hip Hop’, No I.D. He soon became West’s mentor",
                url = "https://centralsauce.com/wp-content/uploads/2018/05/cd2.jpg",
                list = listOf(
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/06/Kanye-West-And-Kid-Cudi-Kids-See-Ghosts-album-cover-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg"
                )
            ),
            AlbumEntity(
                id = auto_id++,
                title = "Late Registration",
                description = "Second Kanye's album",
                list = listOf(
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/06/Kanye-West-And-Kid-Cudi-Kids-See-Ghosts-album-cover-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg"
                ),
                url = "https://main-cdn.sbermegamarket.ru/hlr-system/1721354927/100025355884b0.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "Graduation",
                description = "West started to get more and more involved in the hip-hop scene of the city and when he was 17, he wrote a rap song called ‘Green Eggs and Ham’. He persuaded his mother to pay him money so that he can start recording in the studio. Although his mother did not want this for him, she started accompanying him to a small basement studio in the city. There west met ‘The Godfather of Chicago Hip Hop’, No I.D. He soon became West’s mentor.",
                list = listOf(
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/06/Kanye-West-And-Kid-Cudi-Kids-See-Ghosts-album-cover-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg"
                ),
                url = "https://www.bibdsl.co.uk/product-images/m/l/mus000238311.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "808s & Heartbreak",
                description = "Kanye Kanye Album",
                list = listOf(
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/06/Kanye-West-And-Kid-Cudi-Kids-See-Ghosts-album-cover-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg"
                ),
                url = "https://imagine-club.com/sites/default/files/photos/808s_heartbreak_west_kanye_2_lp_universal_eu.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "My Dark Twisted Fantasy",
                description = "New Kanye Album",
                list = listOf(
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/06/Kanye-West-And-Kid-Cudi-Kids-See-Ghosts-album-cover-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2019/05/Kanye-West-Ye-album-cover-web-optimised-820-1024x1024.jpg",
                    "https://www.udiscovermusic.com/wp-content/uploads/2018/08/Kanye-West-Graduation-album-cover-web-optimised-820-1024x1024.jpg"
                ),
                url = "https://satchmi.com/wp-content/uploads/2020/02/91AMDeXKxFL._SL1500_.jpg",
            )
        )
    }

    fun add(pos: Int?, title: String, description: String) {
        if (pos == null || pos > albums.size || pos < 0) {
            albums.add(AlbumEntity(auto_id++, title, description, auto_url, auto_list))
        } else update(pos, title, description)
    }

    private fun update(pos: Int, title: String, description: String) {
        albums.add(pos, AlbumEntity(auto_id++, title, description, auto_url, auto_list))
    }

    fun delete(pos: Int) {
        albums.removeAt(pos)
    }

    fun getAlbumById(id: Int): AlbumEntity {
        return albums[id]
    }

    fun getAlbumByIndex(index: Int): AlbumEntity {
        return albums[index]
    }

    fun getList(): ArrayList<AlbumEntity> {
        return albums
    }

    fun getIndex(item: AlbumEntity): Int {
        return albums.indexOf(item)
    }

}
