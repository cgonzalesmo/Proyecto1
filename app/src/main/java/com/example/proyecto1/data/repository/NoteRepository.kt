package com.example.proyecto1.data.repository


import androidx.lifecycle.LiveData
import com.example.proyecto1.data.notes.Notes
import com.example.proyecto1.data.notes.NotesDao


class NoteRepository(
    private val dao: NotesDao
) {

    suspend fun insertNotes(notes: Notes) {
        dao.insertNote(notes)
    }

    suspend fun updateNotes(notes: Notes){
        dao.updateNote(notes)
    }
    suspend fun deleteNotes(notes: Notes){
        dao.deleteNote(notes)
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getAllNotes()
    }



}