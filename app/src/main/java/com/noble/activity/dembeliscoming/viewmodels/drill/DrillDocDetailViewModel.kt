package com.noble.activity.dembeliscoming.viewmodels.drill

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.drill.DrillDoc
import com.noble.activity.dembeliscoming.data.drill.DrillDocRepository

class DrillDocDetailViewModel(
    drillDocRepository: DrillDocRepository,
    private val drillDocId: String
) : ViewModel() {
    val drillDoc: LiveData<DrillDoc>

    init {
        drillDoc = drillDocRepository.getDrillDoc(drillDocId)
    }
}