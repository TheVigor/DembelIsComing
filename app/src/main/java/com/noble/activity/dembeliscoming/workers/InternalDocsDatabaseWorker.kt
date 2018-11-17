package com.noble.activity.dembeliscoming.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.internal.InternalDoc
import com.noble.activity.dembeliscoming.utilities.INTERNAL_DOCS_FILENAME

class InternalDocsDatabaseWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    private val TAG by lazy {InternalDocsDatabaseWorker::class.java.simpleName}

    override fun doWork(): Result {

        val internalDocType = object: TypeToken<List<InternalDoc>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            var inputStream = applicationContext.assets.open(INTERNAL_DOCS_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val internalDocList: List<InternalDoc> = Gson().fromJson(jsonReader, internalDocType)
            val database = AppDatabase.getInstance(applicationContext)
            database.internalDocDao().insertAll(internalDocList)
            Result.SUCCESS
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding internal docs", ex)
            Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}