package com.noble.activity.dembeliscoming.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.noble.activity.dembeliscoming.data.drill.DrillDoc
import com.noble.activity.dembeliscoming.data.drill.DrillDocDao
import com.noble.activity.dembeliscoming.data.internal.InternalDoc
import com.noble.activity.dembeliscoming.data.internal.InternalDocDao
import com.noble.activity.dembeliscoming.utilities.DATABASE_NAME
import com.noble.activity.dembeliscoming.workers.DrillDocsDatabaseWorker
import com.noble.activity.dembeliscoming.workers.InternalDocsDatabaseWorker

@Database(entities = [DrillDoc::class, InternalDoc::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drillDocDao() : DrillDocDao
    abstract fun internalDocDao() : InternalDocDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val drillDocRequest = OneTimeWorkRequestBuilder<DrillDocsDatabaseWorker>().build()
                        WorkManager.getInstance().enqueue(drillDocRequest)

                        val internalDocRequest = OneTimeWorkRequestBuilder<InternalDocsDatabaseWorker>().build()
                        WorkManager.getInstance().enqueue(internalDocRequest)

                    }
                })
                .build()
        }

    }
}