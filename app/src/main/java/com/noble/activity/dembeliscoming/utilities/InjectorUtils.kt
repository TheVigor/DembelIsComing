package com.noble.activity.dembeliscoming.utilities

import android.content.Context
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.DrillDocRepository

object InjectorUtils {

    private fun getDrillDocRepository(context: Context): DrillDocRepository {
        return DrillDocRepository.getInstance(AppDatabase.getInstance(context).drillDocDao())
    }
}