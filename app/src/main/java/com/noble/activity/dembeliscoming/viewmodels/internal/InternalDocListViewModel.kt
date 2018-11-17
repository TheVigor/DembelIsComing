package com.noble.activity.dembeliscoming.viewmodels.internal

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.internal.InternalDoc
import com.noble.activity.dembeliscoming.data.internal.InternalDocRepository

class InternalDocListViewModel internal constructor(
    private val internalDocRepository: InternalDocRepository
) : ViewModel() {
    private val internalDocList = MediatorLiveData<List<InternalDoc>>()

    init {
        val actualInternalDocList = internalDocRepository.getInternalDocs()

        internalDocList.addSource(actualInternalDocList, internalDocList::setValue)
    }

    fun getInternalDocs() = internalDocList
}