package com.noble.activity.dembeliscoming.adapters.note

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.noble.activity.dembeliscoming.data.note.Note
import com.noble.activity.dembeliscoming.databinding.ListItemNoteBinding

class NoteAdapter : ListAdapter<Note,
        NoteAdapter.ViewHolder>(NoteDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.apply {
            bind(createOnClickListener(note.id), note)
            //itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(internalDocId: String): View.OnClickListener {
        return View.OnClickListener {
            //val direction = InternalDocFragmentDirections.ActionInternalDocFragmentToInternalDocDetailFragment(internalDocId)
            //it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Note) {
            binding.apply {
                //clickListener = listener
                //internalDoc = item
                executePendingBindings()
            }
        }
    }
}