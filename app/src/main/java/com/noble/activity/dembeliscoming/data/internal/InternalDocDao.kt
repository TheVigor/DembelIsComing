package com.noble.activity.dembeliscoming.data.internal

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface InternalDocDao {
    @Query("SELECT * FROM internal_docs ORDER BY type")
    fun getInternalDocs() : LiveData<List<InternalDoc>>

    @Query("SELECT * FROM internal_docs WHERE id = :internalDocId")
    fun getInternalDoc(internalDocId: String): LiveData<InternalDoc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(internalDocs: List<InternalDoc>)
}