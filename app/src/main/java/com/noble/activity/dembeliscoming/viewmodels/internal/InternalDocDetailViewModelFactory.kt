package com.noble.activity.dembeliscoming.viewmodels.internal

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.dembeliscoming.data.internal.InternalDocRepository

class InternalDocDetailViewModelFactory(
    private val internalDocRepository: InternalDocRepository,
    private val internalDocId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InternalDocDetailViewModel(internalDocRepository, internalDocId) as T
    }
}