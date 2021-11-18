package com.example.androidsemthree.rep

import com.example.androidsemthree.model.AlbumEntity

object AlbumRepository {
    private var albums = ArrayList<AlbumEntity>()
    private var auto_id = 0
    private var auto_url =
        "https://w7.pngwing.com/pngs/72/42/png-transparent-vkontakte-social-networking-service-account-user-facebook-anonymous-mask-miscellaneous-blue-people.png"

    init {
        albums = arrayListOf(
            AlbumEntity(
                id = auto_id++,
                title = "College Dropout",
                description = "Kanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut AlbumKanye West Debut Album",
                url = "https://centralsauce.com/wp-content/uploads/2018/05/cd2.jpg",
                list =  listOf("https://static.wixstatic.com/media/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg/v1/fill/w_320,h_385,q_90/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg", "https://dkmk.ru/images/barnwoodenhouse.jpeg", "https://smartdom5.ru/img/19399376_1920_q70.jpg")
            ),
            AlbumEntity(
                id = auto_id++,
                title = "Late Registration",
                description = "Second Kanye's album",
                url = "https://main-cdn.sbermegamarket.ru/hlr-system/1721354927/100025355884b0.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "Graduation",
                description = "Kanye End",
                url = "https://www.bibdsl.co.uk/product-images/m/l/mus000238311.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "808s & Heartbreak",
                description = "Kanye Kanye Album",
                url = "https://imagine-club.com/sites/default/files/photos/808s_heartbreak_west_kanye_2_lp_universal_eu.jpg",
            ),
            AlbumEntity(
                id = auto_id++,
                title = "My Dark Twisted Fantasy",
                description = "New Kanye Album",
                url = "https://satchmi.com/wp-content/uploads/2020/02/91AMDeXKxFL._SL1500_.jpg",
            )
        )
    }

    fun add(pos: Int?, title: String, description: String) {
        if (pos == null || pos > albums.size || pos < 0) {
            albums.add(AlbumEntity(auto_id++, title, description, auto_url))
        } else update(pos, title, description)
    }

    private fun update(pos: Int, title: String, description: String) {
        albums.add(pos, AlbumEntity(auto_id++, title, description, auto_url))
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
