package com.noble.activity.dembeliscoming.data.note

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao interface  NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNote(noteId: String): LiveData<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note): Int

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteNoteById(noteId: String): Int

    @Query("DELETE FROM notes")
    fun deleteNotes()




}