package com.example.gymtracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "activity_table")
data class GymActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val activity: String,
    val weight: Int,
    val sets: Int,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("d MMMM yyyy | HH:mm", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
}
