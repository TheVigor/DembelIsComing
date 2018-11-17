package com.noble.activity.dembeliscoming.viewmodels.internal

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.dembeliscoming.data.internal.InternalDocRepository

class InternalDocListViewModelFactory(
    private val repository: InternalDocRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        InternalDocListViewModel(repository) as T
}