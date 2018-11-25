package com.noble.activity.dembeliscoming.viewmodels.note

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.dembeliscoming.data.note.NoteRepository

class NoteAddEditViewModelFactory(
    private val repository: NoteRepository,
    private val noteId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        NoteAddEditViewModel(repository, noteId) as T
}