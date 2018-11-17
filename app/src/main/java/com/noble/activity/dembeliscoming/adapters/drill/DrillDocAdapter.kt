package com.noble.activity.dembeliscoming.adapters.drill

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.noble.activity.dembeliscoming.data.drill.DrillDoc
import com.noble.activity.dembeliscoming.databinding.ListItemDrillDocBinding
import com.noble.activity.dembeliscoming.fragments.main.drill.DrillDocFragmentDirections

class DrillDocAdapter : ListAdapter<DrillDoc,
        DrillDocAdapter.ViewHolder>(DrillDocDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drillDoc = getItem(position)
        holder.apply {
            bind(createOnClickListener(drillDoc.docId), drillDoc)
            //itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemDrillDocBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(drillDocId: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = DrillDocFragmentDirections.ActionDrillDocFragmentToDrillDocDetailFragment(drillDocId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemDrillDocBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: DrillDoc) {
            binding.apply {
                clickListener = listener
                drillDoc = item
                executePendingBindings()
            }
        }
    }
}