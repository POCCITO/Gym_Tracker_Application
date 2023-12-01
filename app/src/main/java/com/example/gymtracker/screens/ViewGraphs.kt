package com.example.gymtracker.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gymtracker.MainActivity
import com.example.gymtracker.R
import com.example.gymtracker.database.MyApp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ViewGraphs : AppCompatActivity() {
    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_graphs)

        // Dropdown
        val spinnerId = findViewById<Spinner>(R.id.selectActivityInputID)

        val activities = resources.getStringArray(R.array.activities2)

        val arrayAdp =
            ArrayAdapter(
                this@ViewGraphs,
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
                val selectedActivity = activities[position]
                Toast.makeText(
                    this@ViewGraphs,
                    "Selected: $selectedActivity",
                    Toast.LENGTH_SHORT
                ).show()

                lineChart = findViewById(R.id.lineChart)

                lineChart.setBackgroundColor(ContextCompat.getColor(this@ViewGraphs, R.color.black))

                GlobalScope.launch {
                    val weightsList: List<Int> =
                        MyApp.database.gymActivityDao().getWeightsByActivity(selectedActivity)
//                    val timestampsList: List<Long> = MyApp.database.gymActivityDao().getAllDates()

                    val entries = weightsList.mapIndexed { index, weight ->
                        Entry(index.toFloat(), weight.toFloat())
                    }

                    val dataSet = LineDataSet(entries, "Lifting Weight over Time (kg)")
                    dataSet.color = ContextCompat.getColor(this@ViewGraphs, R.color.purple)
                    dataSet.lineWidth = 4f
                    dataSet.valueTextColor =
                        ContextCompat.getColor(this@ViewGraphs, R.color.white)
                    dataSet.valueTextSize = 15f

                    val circleColors = List(weightsList.size) {
                        ContextCompat.getColor(
                            this@ViewGraphs,
                            R.color.white
                        )
                    }
                    dataSet.circleColors = circleColors
                    dataSet.circleRadius = 6f

                    dataSet.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return "${value.toInt()} kg"
                        }
                    }

                    val lineData = LineData(dataSet)

                    val xAxis = lineChart.xAxis
                    xAxis.isEnabled = false

                    val axisLeft = lineChart.axisLeft
                    val axisRight = lineChart.axisRight
                    axisRight.textColor = ContextCompat.getColor(this@ViewGraphs, R.color.white)
                    axisRight.textSize = 10f
                    axisLeft.textColor = ContextCompat.getColor(this@ViewGraphs, R.color.white)
                    axisLeft.textSize = 10f

                    val legend = lineChart.legend
                    legend.isEnabled = false

                    val description = lineChart.description
                    description.isEnabled = false

                    lineChart.data = lineData
                    lineChart.invalidate()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing here
            }
        }
    }

//    private fun formatDate(timestamp: Long): String {
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
//        return dateFormat.format(Date(timestamp))
//    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
