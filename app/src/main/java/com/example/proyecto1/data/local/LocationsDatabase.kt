package com.example.proyecto1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Locations::class], version = 3)
abstract class LocationsDatabase : RoomDatabase(){
    abstract val dao: LocationsDao
}