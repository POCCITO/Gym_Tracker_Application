package com.example.gymtracker.database

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyApp : Application() {

    companion object {
        lateinit var database: GymDatabase
        lateinit var appInstance: MyApp

        fun recreateDatabase() {
            appInstance.applicationContext.deleteDatabase("gym_database")

            database = Room.databaseBuilder(
                appInstance.applicationContext,
                GymDatabase::class.java, "gym_database"
            ).build()
        }

        fun getDatabaseInstance(): GymDatabase {
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        database = Room.databaseBuilder(
            applicationContext,
            GymDatabase::class.java, "gym_database"
        ).build()
    }
}
