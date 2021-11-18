package com.example.androidsemthree.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.androidsemthree.databinding.ItemAlbumBinding
import com.example.androidsemthree.model.AlbumEntity
import com.example.androidsemthree.rep.AlbumRepository

class AlbumHolder(
    private val binding: ItemAlbumBinding,
    private val glide: RequestManager,
    private val deleteAction: (Int?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var album: AlbumEntity? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: AlbumEntity) {
        this.album = item
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            glide.load(item.url)
                .apply(options)
                .into(ivImage)
            btnDelete.setOnClickListener {
                deleteAction(AlbumRepository.getIndex(item))
            }
        }
    }

    fun updateFields(bundle: Bundle) {
        bundle.run {
            getString("TITLE")?.also {
                updateTitle(it)
            }
            getString("DESCRIPTION")?.also {
                updateDescription(it)
            }
            getString("URL")?.also {
                updateUrl(it)
            }
        }
    }

    private fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun updateDescription(desc: String) {
        binding.tvDescription.text = desc
    }

    private fun updateUrl(url: String) {
        glide.load(url)
            .apply(options)
            .into(binding.ivImage)
    }


    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            deleteAction: (Int?) -> Unit
        ) = AlbumHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, deleteAction
        )
    }
}
