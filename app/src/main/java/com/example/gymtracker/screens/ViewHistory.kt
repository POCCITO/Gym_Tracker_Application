package com.example.gymtracker.screens

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtracker.MainActivity
import com.example.gymtracker.R
import com.example.gymtracker.database.GymActivity
import com.example.gymtracker.database.MyApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_history)

        val detailsTextView = findViewById<TextView>(R.id.displayHistoryID)

        GlobalScope.launch {
            val detailsList: List<GymActivity> = MyApp.database.gymActivityDao().getAllActivities()

            val detailsText = if (detailsList.isEmpty()) {
                "No data available..."
            } else {
                buildString {
                    for (detail in detailsList) {
                        append("<b>${detail.getFormattedDate()}</b><br/>")
                        append("${detail.activity} ${detail.weight} kg ${detail.sets} sets<br/><br/>")
                    }
                }
            }

            runOnUiThread {
                detailsTextView.text = Html.fromHtml(detailsText, Html.FROM_HTML_MODE_LEGACY)
            }
        }
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
