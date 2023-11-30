package com.example.gymtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.gymtracker.database.MyApp
import com.example.gymtracker.screens.AddActivity
import com.example.gymtracker.screens.ViewGraphs
import com.example.gymtracker.screens.ViewHistory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnResetUserData = findViewById<Button>(R.id.btnResetUserData)

        btnResetUserData.setOnLongClickListener {
            resetUserData(it)
            true
        }

    }

    fun resetUserData(view: View) {
        MyApp.recreateDatabase()
        Toast.makeText(this@MainActivity, "User data has been reset", Toast.LENGTH_SHORT).show()
    }

    fun goToAddActivity(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun goToViewHistory(view: View) {
        val intent = Intent(this, ViewHistory::class.java)
        startActivity(intent)
    }

    fun goToViewGraphs(view: View) {
        val intent = Intent(this, ViewGraphs::class.java)
        startActivity(intent)
    }
}