package com.example.proyecto1.data.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNote(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY notesName ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}