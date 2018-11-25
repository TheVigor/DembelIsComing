package com.noble.activity.dembeliscoming.adapters.note

import android.support.v7.util.DiffUtil
import com.noble.activity.dembeliscoming.data.note.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}