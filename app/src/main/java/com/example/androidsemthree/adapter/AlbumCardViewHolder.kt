package com.example.androidsemthree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.androidsemthree.databinding.ItemCvAlbumBinding
import com.example.androidsemthree.model.AlbumEntity

class AlbumCardViewHolder(
    private val binding: ItemCvAlbumBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root) {

    private var album: AlbumEntity? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: AlbumEntity) {
        this.album = item
        with(binding) {
            tvTitle1.text = item.title
            tvTitle2.text = item.title
            tvDesc.text = item.description
            glide.load(item.url)
                .apply(options)
                .into(ivImage1)
            vp2Images.adapter = item.list?.let { ViewPagerAdapter(it, glide) }
        }

    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
        ) = AlbumCardViewHolder(
            ItemCvAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide
        )
    }
}
