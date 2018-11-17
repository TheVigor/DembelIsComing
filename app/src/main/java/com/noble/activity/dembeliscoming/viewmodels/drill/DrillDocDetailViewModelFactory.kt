package com.noble.activity.dembeliscoming.viewmodels.drill

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.dembeliscoming.data.drill.DrillDocRepository

class DrillDocDetailViewModelFactory(
    private val drillDocRepository: DrillDocRepository,
    private val drillDocId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DrillDocDetailViewModel(
            drillDocRepository,
            drillDocId
        ) as T
    }
}