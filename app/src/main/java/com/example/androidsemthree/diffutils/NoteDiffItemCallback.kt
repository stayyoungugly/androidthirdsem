package com.example.androidsemthree.diffutils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.androidsemthree.models.DateToString
import com.example.androidsemthree.models.Note

class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean {
        return oldItem.equals(newItem)
    }

    override fun getChangePayload(
        oldItem: Note,
        newItem: Note
    ): Any? {
        val bundle = Bundle().apply {
            if (oldItem.title != newItem.title) {
                putString("TITLE", newItem.title)
            }

            if (oldItem.description != newItem.description) {
                putString("DESCRIPTION", newItem.description)
            }

            if (oldItem.date != newItem.date) {
                putString("DATE", newItem.date?.let { DateToString.convertDateToString(it) })
            }

            if (oldItem.latitude != newItem.latitude) {
                newItem.latitude?.let {
                    putDouble("LATITUDE", it)
                }
            }

            if (oldItem.longitude != newItem.longitude) {
                newItem.longitude?.let {
                    putDouble("LONGITUDE", it)
                }
            }
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}
