package com.noble.activity.dembeliscoming.data.internal

class InternalDocRepository private constructor(private val internalDocDao: InternalDocDao) {

    fun getInternalDocs() = internalDocDao.getInternalDocs()

    fun getInternalDoc(internalDocId: String) = internalDocDao.getInternalDoc(internalDocId)

    companion object {

        @Volatile private var instance: InternalDocRepository? = null

        fun getInstance(internalDocDao: InternalDocDao) =
            instance ?: synchronized(this) {
                instance
                    ?: InternalDocRepository(internalDocDao).also { instance = it }
            }
    }
}