package com.example.androidsemthree.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.androidsemthree.diffutils.AlbumDiffItemCallback
import com.example.androidsemthree.model.AlbumEntity

class AlbumListAdapter(
    private val glide: RequestManager,
    private val deleteAction: (Int?) -> Unit
) : ListAdapter<AlbumEntity, AlbumHolder>(AlbumDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumHolder = AlbumHolder.create(parent, glide, deleteAction)

    override fun onBindViewHolder(
        holder: AlbumHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: AlbumHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<AlbumEntity>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}

