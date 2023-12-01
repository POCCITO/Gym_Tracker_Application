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

//    @Query("SELECT weight FROM activity_table")
//    suspend fun getAllWeights(): List<Int>

    @Query("SELECT weight FROM activity_table WHERE activity = :activity")
    suspend fun getWeightsByActivity(activity: String): List<Int>

//    @Query("SELECT timestamp FROM activity_table")
//    suspend fun getAllDates(): List<Long>

//    @Query("DELETE FROM activity_table")
//    suspend fun clearAllActivities()
}
