package com.example.androidsemthree.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsemthree.databinding.NoteItemBinding
import com.example.androidsemthree.models.Note

class NoteHolder(
    private val binding: NoteItemBinding,
    private val actionChoose: (Int) -> (Unit),
    private val actionDelete: (Int) -> (Unit)
) : RecyclerView.ViewHolder(binding.root) {
    private var note: Note? = null

    fun bind(item: Note) {
        this.note = item
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description

            itemView.setOnClickListener {
                item.id?.let { it1 -> actionChoose(it1) }
            }
            ivDelete.setOnClickListener {
                item.id?.let { it1 -> actionDelete(it1) }
            }
        }
    }

    fun updateFields(bundle: Bundle?) {
        bundle?.run {
            getString("TITLE")?.also {
                updateTitle(it)
            }
            getString("DESCRIPTION")?.also {
                updateDescription(it)
            }
        }
    }

    private fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun updateDescription(description: String) {
        binding.tvDescription.text = description
    }

    companion object {
        fun create(
            parent: ViewGroup,
            actionChoose: (Int) -> Unit,
            actionDelete: (Int) -> Unit
        ) = NoteHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            actionChoose,
            actionDelete
        )
    }
}
