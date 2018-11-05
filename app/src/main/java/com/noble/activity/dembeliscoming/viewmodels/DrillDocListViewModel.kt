package com.noble.activity.dembeliscoming.viewmodels

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.DrillDoc
import com.noble.activity.dembeliscoming.data.DrillDocRepository

class DrillDocListViewModel internal constructor(
    private val drillDocRepository: DrillDocRepository
) : ViewModel() {
    private val drillDocList = MediatorLiveData<List<DrillDoc>>()

    init {
        val actualDrillDocList = drillDocRepository.getDrillDocs()

        drillDocList.addSource(actualDrillDocList, drillDocList::setValue)
    }

    fun getDrillDocs() = drillDocList
}