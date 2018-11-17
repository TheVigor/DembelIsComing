package com.noble.activity.dembeliscoming.adapters.internal

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.noble.activity.dembeliscoming.data.internal.InternalDoc
import com.noble.activity.dembeliscoming.databinding.ListItemInternalDocBinding
import com.noble.activity.dembeliscoming.fragments.main.internal.InternalDocFragmentDirections

class InternalDocAdapter : ListAdapter<InternalDoc,
        InternalDocAdapter.ViewHolder>(InternalDocDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val internalDoc = getItem(position)
        holder.apply {
            bind(createOnClickListener(internalDoc.docId), internalDoc)
            //itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemInternalDocBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(internalDocId: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = InternalDocFragmentDirections.ActionInternalDocFragmentToInternalDocDetailFragment(internalDocId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemInternalDocBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: InternalDoc) {
            binding.apply {
                clickListener = listener
                internalDoc = item
                executePendingBindings()
            }
        }
    }
}