package com.example.androidsemthree.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.androidsemthree.model.AlbumEntity

class AlbumCardViewAdapter(
    private val list: List<AlbumEntity>,
    private val glide: RequestManager,
) : RecyclerView.Adapter<AlbumCardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumCardViewHolder = AlbumCardViewHolder.create(parent, glide)

    override fun onBindViewHolder(holder: AlbumCardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
