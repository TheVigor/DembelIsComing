package com.noble.activity.dembeliscoming.data.note

class NoteRepository private constructor(private val noteDao: NoteDao) {

    fun getNotes() = noteDao.getNotes()
    fun getNote(noteId: String) = noteDao.getNote(noteId)

    fun deleteNotes() = noteDao.deleteNotes()
    fun deleteNote(noteId: String) = noteDao.deleteNote(noteId)

    fun insertNote(note: Note) = noteDao.insertNote(note)
    fun updateNote(note: Note) = noteDao.updateNote(note)

    companion object {

        @Volatile private var instance: NoteRepository? = null

        fun getInstance(noteDao: NoteDao) =
            instance ?: synchronized(this) {
                instance
                    ?: NoteRepository(noteDao).also { instance = it }
            }
    }
}