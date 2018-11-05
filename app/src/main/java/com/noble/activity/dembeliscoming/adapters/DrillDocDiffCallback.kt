package com.noble.activity.dembeliscoming.adapters

import android.support.v7.util.DiffUtil
import com.noble.activity.dembeliscoming.data.DrillDoc

class DrillDocDiffCallback : DiffUtil.ItemCallback<DrillDoc>() {

    override fun areItemsTheSame(oldItem: DrillDoc, newItem: DrillDoc): Boolean {
        return oldItem.docId == newItem.docId
    }

    override fun areContentsTheSame(oldItem: DrillDoc, newItem: DrillDoc): Boolean {
        return oldItem == newItem
    }
}