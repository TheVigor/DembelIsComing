package com.noble.activity.dembeliscoming.data.drill

class DrillDocRepository private constructor(private val drillDocDao: DrillDocDao) {

    fun getDrillDocs() = drillDocDao.getDrillDocs()

    fun getDrillDoc(drillDocId: String) = drillDocDao.getDrillDoc(drillDocId)

    companion object {

        @Volatile private var instance: DrillDocRepository? = null

        fun getInstance(drillDocDao: DrillDocDao) =
                instance ?: synchronized(this) {
                    instance
                        ?: DrillDocRepository(drillDocDao).also { instance = it }
                }
    }
}