package com.noble.activity.dembeliscoming.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.DrillDoc
import com.noble.activity.dembeliscoming.data.DrillDocRepository

class DrillDocDetailViewModel(
    drillDocRepository: DrillDocRepository,
    private val drillDocId: String
) : ViewModel() {
    val drillDoc: LiveData<DrillDoc>

    init {
        drillDoc = drillDocRepository.getDrillDoc(drillDocId)
    }
}