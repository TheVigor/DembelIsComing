package com.noble.activity.dembeliscoming.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.dembeliscoming.data.DrillDocRepository

class DrillDocListViewModelFactory(
    private val repository: DrillDocRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = DrillDocListViewModel(repository) as T
}