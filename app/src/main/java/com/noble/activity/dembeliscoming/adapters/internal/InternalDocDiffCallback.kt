package com.noble.activity.dembeliscoming.adapters.internal

import android.support.v7.util.DiffUtil
import com.noble.activity.dembeliscoming.data.internal.InternalDoc

class InternalDocDiffCallback : DiffUtil.ItemCallback<InternalDoc>() {

    override fun areItemsTheSame(oldItem: InternalDoc, newItem: InternalDoc): Boolean {
        return oldItem.docId == newItem.docId
    }

    override fun areContentsTheSame(oldItem: InternalDoc, newItem: InternalDoc): Boolean {
        return oldItem == newItem
    }
}