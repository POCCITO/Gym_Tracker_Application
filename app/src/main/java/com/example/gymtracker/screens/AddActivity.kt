package com.example.gymtracker.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtracker.MainActivity
import com.example.gymtracker.R
import com.example.gymtracker.database.GymActivity
import com.example.gymtracker.database.MyApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        val spinnerId = findViewById<Spinner>(R.id.activityInputID)

        val activities = resources.getStringArray(R.array.activities)

        val arrayAdp =
            ArrayAdapter(
                this@AddActivity,
                android.R.layout.simple_spinner_dropdown_item,
                activities
            )
        spinnerId.adapter = arrayAdp

        spinnerId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                if(position != 0) {
                    val selectedActivity = activities[position]
                    Toast.makeText(
                        this@AddActivity,
                        "Selected: $selectedActivity",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing here
            }
        }

        val btnSubmitActivity = findViewById<Button>(R.id.btnSubmitActivity)

        btnSubmitActivity.setOnClickListener {
            submitActivity()
        }
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun submitActivity() {
        val activityInput = findViewById<Spinner>(R.id.activityInputID).selectedItem.toString()
        val weightInputText = findViewById<EditText>(R.id.weightInputID).text.toString()
        val setInputText = findViewById<EditText>(R.id.setInputID).text.toString()

        if (activityInput.isBlank() || weightInputText.isBlank() || setInputText.isBlank()) {
            Toast.makeText(this@AddActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert to integers
        val weightInput = weightInputText.toInt()
        val setInput = setInputText.toInt()

        val timestamp = System.currentTimeMillis()

        val activity = GymActivity(
            activity = activityInput,
            weight = weightInput,
            sets = setInput,
            timestamp = timestamp
        )

        GlobalScope.launch {
            try {
                MyApp.database.gymActivityDao().insert(activity)
                val intent = Intent(this@AddActivity, ActivityAdded::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@AddActivity,
                        "An error occurred while saving your data. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
