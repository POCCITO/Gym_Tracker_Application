package com.example.gymtracker.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtracker.MainActivity
import com.example.gymtracker.R

class ActivityAdded : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added)
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToAddActivity(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
}