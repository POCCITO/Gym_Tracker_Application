package com.example.gymtracker.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtracker.MainActivity
import com.example.gymtracker.R
import com.example.gymtracker.database.GymActivity
import com.example.gymtracker.database.MyApp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewGraphs : AppCompatActivity() {
    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_graphs)

        lineChart = findViewById(R.id.lineChart)

        GlobalScope.launch {
            val weightsList: List<Int> = MyApp.database.gymActivityDao().getAllWeights()

            val entries = weightsList.mapIndexed { index, weight ->
                Entry((index + 1).toFloat(), weight.toFloat())
            }

            val dataSet = LineDataSet(entries, "Weight")

            val lineData = LineData(dataSet)

            lineChart.data = lineData

//            val description = Description()
//            description.text = "Chart Description"
//            lineChart.description = description

            lineChart.invalidate()
        }
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

