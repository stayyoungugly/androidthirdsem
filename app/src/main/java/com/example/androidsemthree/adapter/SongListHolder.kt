package com.example.androidsemthree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsemthree.databinding.ItemSongBinding
import com.example.androidsemthree.model.Song

class SongListHolder(
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var song: Song? = null

    fun bind(item: Song) {
        this.song = item
        with(binding) {
            tvTitle.text = item.title
            tvAuthor.text = item.author
            ivCover.setImageResource(item.cover)
            itemView.setOnClickListener {
                action(item.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = SongListHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}
