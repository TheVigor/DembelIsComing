package com.noble.activity.dembeliscoming.viewmodels.note

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.dembeliscoming.data.note.Note
import com.noble.activity.dembeliscoming.data.note.NoteRepository

class NoteAddEditViewModel(
    private val noteRepository: NoteRepository,
    private val noteId: String
) : ViewModel() {
    val note: LiveData<Note> = if (noteId.isEmpty()) {
        MutableLiveData<Note>().apply { postValue(Note("0", "","","0")) }
    } else {
        noteRepository.getNote(noteId)
    }

    fun saveTask(title: String, description: String) {
        val note = Note(title = title, description = description)

        noteRepository.insertNote(note)

    }

}

