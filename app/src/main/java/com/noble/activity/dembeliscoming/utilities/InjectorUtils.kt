package com.noble.activity.dembeliscoming.utilities

import android.content.Context
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.drill.DrillDocRepository
import com.noble.activity.dembeliscoming.viewmodels.drill.DrillDocDetailViewModelFactory
import com.noble.activity.dembeliscoming.viewmodels.drill.DrillDocListViewModelFactory

object InjectorUtils {

    private fun getDrillDocRepository(context: Context): DrillDocRepository {
        return DrillDocRepository.getInstance(AppDatabase.getInstance(context).drillDocDao())
    }

    fun provideDrillDocListViewModelFactory(context: Context): DrillDocListViewModelFactory {
        val repository = getDrillDocRepository(context)
        return DrillDocListViewModelFactory(repository)
    }

    fun provideDrillDocDetailViewModelFactory(
        context: Context,
        drillDocId: String
    ): DrillDocDetailViewModelFactory {
        return DrillDocDetailViewModelFactory(
            getDrillDocRepository(context),
            drillDocId
        )
    }

}