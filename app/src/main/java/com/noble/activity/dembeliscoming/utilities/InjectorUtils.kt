package com.noble.activity.dembeliscoming.utilities

import android.content.Context
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.DrillDocRepository
import com.noble.activity.dembeliscoming.viewmodels.DrillDocListViewModel
import com.noble.activity.dembeliscoming.viewmodels.DrillDocListViewModelFactory

object InjectorUtils {

    private fun getDrillDocRepository(context: Context): DrillDocRepository {
        return DrillDocRepository.getInstance(AppDatabase.getInstance(context).drillDocDao())
    }

    fun provideDrillDocListViewModelFactory(context: Context): DrillDocListViewModelFactory {
        val repository = getDrillDocRepository(context)
        return DrillDocListViewModelFactory(repository)
    }

}