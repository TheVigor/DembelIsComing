package com.noble.activity.dembeliscoming.data.drill

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface DrillDocDao {
    @Query("SELECT * FROM drill_docs ORDER BY type")
    fun getDrillDocs() : LiveData<List<DrillDoc>>

    @Query("SELECT * FROM drill_docs WHERE id = :drillDocId")
    fun getDrillDoc(drillDocId: String): LiveData<DrillDoc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drillDocs: List<DrillDoc>)
}
