package com.example.androidsemthree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.androidsemthree.databinding.ItemAlbumBinding
import com.example.androidsemthree.model.AlbumEntity

class AlbumHolder(
    private val binding: ItemAlbumBinding,
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var album: AlbumEntity? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: AlbumEntity) {
        this.album = item
        with(binding) {
            tvName.text = item.name
            tvAuthor.text = item.author

            glide.load(item.url)
                .apply(options)
                .into(ivImage)

            itemView.setOnClickListener {
                action(item.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (Int) -> Unit
        ) = AlbumHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )
    }
}
