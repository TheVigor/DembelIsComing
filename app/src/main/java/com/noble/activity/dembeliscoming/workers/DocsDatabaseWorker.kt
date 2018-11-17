package com.noble.activity.dembeliscoming.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.drill.DrillDoc
import com.noble.activity.dembeliscoming.utilities.DRILL_DOCS_FILENAME
import java.lang.Exception

class DocsDatabaseWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    private val TAG by lazy {DocsDatabaseWorker::class.java.simpleName}

    override fun doWork(): Result {
        
        val drillDocType = object: TypeToken<List<DrillDoc>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            var inputStream = applicationContext.assets.open(DRILL_DOCS_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val drillDocList: List<DrillDoc> = Gson().fromJson(jsonReader, drillDocType)
            val database = AppDatabase.getInstance(applicationContext)
            database.drillDocDao().insertAll(drillDocList)
            Result.SUCCESS
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding drill docs", ex)
            Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}



