package com.example.androidsemthree.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsemthree.model.Song

class SongListAdapter(
    private val list: ArrayList<Song>,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<SongListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongListHolder = SongListHolder.create(parent, action)

    override fun onBindViewHolder(holder: SongListHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
