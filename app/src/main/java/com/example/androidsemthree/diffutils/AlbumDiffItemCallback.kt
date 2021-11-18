package com.example.androidsemthree.diffutils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.androidsemthree.model.AlbumEntity

class AlbumDiffItemCallback : DiffUtil.ItemCallback<AlbumEntity>() {

    override fun areItemsTheSame(
        oldItem: AlbumEntity,
        newItem: AlbumEntity
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: AlbumEntity,
        newItem: AlbumEntity
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: AlbumEntity, newItem: AlbumEntity): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("TITLE", newItem.title)
        }
        if (oldItem.description != newItem.description) {
            bundle.putString("DESCRIPTION", newItem.description)
        }
        if (oldItem.url != newItem.url) {
            bundle.putString("URL", newItem.url)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}

