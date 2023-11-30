package com.example.gymtracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GymActivityDao {

    @Insert
    suspend fun insert(activity: GymActivity)

    @Query("SELECT * FROM activity_table")
    suspend fun getAllActivities(): List<GymActivity>

    @Query("SELECT weight FROM activity_table")
    suspend fun getAllWeights(): List<Int>


//    @Query("DELETE FROM activity_table")
//    suspend fun clearAllActivities()
}
