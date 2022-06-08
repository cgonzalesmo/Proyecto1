package com.example.proyecto1.data.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto1.util.Constants.TABLE2_NAME

@Entity(tableName = TABLE2_NAME)
data class Notes (
    val notesName: String,
    val notesDescription: String,
    var notesDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)