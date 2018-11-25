package com.noble.activity.dembeliscoming.viewmodels.note

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.note.Note
import com.noble.activity.dembeliscoming.data.note.NoteRepository

class NoteListViewModel internal constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val noteList = MediatorLiveData<List<Note>>()

    init {
        val actualNoteList = noteRepository.getNotes()

        noteList.addSource(actualNoteList, noteList::setValue)
    }

    fun getNotes() = noteList
}