package com.example.gymtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GymActivity::class], version = 1, exportSchema = false)
abstract class GymDatabase : RoomDatabase() {

    abstract fun gymActivityDao(): GymActivityDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getInstance(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): GymDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                GymDatabase::class.java,
                "gym_database"
            )
                .build()
        }
    }

//    fun clearAllActivities() {
//        GlobalScope.launch {
//            database.gymActivityDao().clearAllActivities()
//        }
//    }
}
