package com.example.proyecto1.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationsDao {
    @Insert
    suspend fun insertLocation(locations: Locations)

    @Query("SELECT * FROM locations_table ORDER BY locationName ASC")
    fun getAllLocations(): LiveData<List<Locations>>
}