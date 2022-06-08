package com.example.proyecto1.data.notes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){
    abstract val dao: NotesDao
}