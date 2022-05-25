package com.example.proyecto1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto1.util.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Locations(
    val locationName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)