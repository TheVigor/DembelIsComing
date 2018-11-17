package com.noble.activity.dembeliscoming.viewmodels.internal

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.internal.InternalDoc
import com.noble.activity.dembeliscoming.data.internal.InternalDocRepository

class InternalDocDetailViewModel(
    internalDocRepository: InternalDocRepository,
    private val internalDocId: String
) : ViewModel() {
    val internalDoc: LiveData<InternalDoc>

    init {
        internalDoc = internalDocRepository.getInternalDoc(internalDocId)
    }
}