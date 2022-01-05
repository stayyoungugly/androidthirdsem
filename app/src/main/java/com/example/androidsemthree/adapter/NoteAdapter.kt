package com.example.androidsemthree.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidsemthree.diffutils.NoteDiffItemCallback
import com.example.androidsemthree.models.Note
import java.util.ArrayList

class NoteAdapter(
    private val actionChoose: (Int) -> (Unit),
    private val actionDelete: (Int) -> (Unit)
) : ListAdapter<Note, NoteHolder>(NoteDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder = NoteHolder.create(parent, actionChoose, actionDelete)

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) = holder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf {
                it is Bundle
            }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Note>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}
