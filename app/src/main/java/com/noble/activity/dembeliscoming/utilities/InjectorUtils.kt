package com.noble.activity.dembeliscoming.utilities

import android.content.Context
import com.noble.activity.dembeliscoming.data.AppDatabase
import com.noble.activity.dembeliscoming.data.drill.DrillDocRepository
import com.noble.activity.dembeliscoming.data.internal.InternalDocRepository
import com.noble.activity.dembeliscoming.data.note.NoteRepository
import com.noble.activity.dembeliscoming.viewmodels.drill.DrillDocDetailViewModelFactory
import com.noble.activity.dembeliscoming.viewmodels.drill.DrillDocListViewModelFactory
import com.noble.activity.dembeliscoming.viewmodels.internal.InternalDocDetailViewModelFactory
import com.noble.activity.dembeliscoming.viewmodels.internal.InternalDocListViewModelFactory

object InjectorUtils {

    private fun getNoteRepository(context: Context): NoteRepository {
        return  NoteRepository.getInstance(AppDatabase.getInstance(context).noteDao())
    }

    private fun getInternalDocRepository(context: Context): InternalDocRepository {
        return InternalDocRepository.getInstance(AppDatabase.getInstance(context).internalDocDao())
    }

    fun provideInternalDocListViewModelFactory(context: Context): InternalDocListViewModelFactory {
        val repository = getInternalDocRepository(context)
        return InternalDocListViewModelFactory(repository)
    }

    fun provideInternalDocDetailViewModelFactory(
        context: Context,
        internalDocId: String
    ): InternalDocDetailViewModelFactory {
        return InternalDocDetailViewModelFactory(getInternalDocRepository(context), internalDocId)
    }

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
        return DrillDocDetailViewModelFactory(getDrillDocRepository(context), drillDocId)
    }

}