package com.example.proyecto1.data.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY notesName ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}