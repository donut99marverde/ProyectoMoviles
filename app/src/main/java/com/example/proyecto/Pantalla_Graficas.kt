package com.example.proyecto

import android.graphics.Color
import android.graphics.Color.red
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyecto.databinding.GraficaHabitosBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

import java.security.KeyStore


class Pantalla_Graficas : AppCompatActivity() {

    private lateinit var binding: GraficaHabitosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GraficaHabitosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val weeklyButton = findViewById<Button>(R.id.weeklyButton)
        val monthlyButton = findViewById<Button>(R.id.monthlyButton)

        weeklyButton.setOnClickListener {
            buildGraph("weekly")
        }

        monthlyButton.setOnClickListener {
            buildGraph("monthly")
        }

        buildGraph("weekly")
    }


    private fun buildGraph(type: String) {
        val habitManager = HabitManager(this)
        var stats: ArrayList<Stat>

        if(type == "weekly") {
            stats = habitManager.getWeeklyStats()
        } else {
            stats = habitManager.getMonthlyStats()
        }

        val colors = mutableListOf<Int>(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.DKGRAY, Color.CYAN, Color.LTGRAY)
        val entries: MutableList<BarEntry> = ArrayList()
        val legends = arrayListOf<LegendEntry>()
        val graphTextView = findViewById<TextView>(R.id.graphTextView)
        var colorIndex = 0
        var statIndex = 0
        var counter = 0

        for(stat in stats) {
            entries.add(BarEntry((statIndex + 1).toFloat(), stat.completed.toFloat()))
            legends.add(LegendEntry((statIndex + 1).toString() + ". " + stat.category, Legend.LegendForm.CIRCLE, 10f,0f,null, colors[colorIndex]))
            colorIndex++
            statIndex++
            if(colorIndex >= colors.size) {
                colorIndex = 0
            }
            counter += stat.completed
        }

        if(counter == 0) {
            graphTextView.text = "No se ha completado ningun hábito esta semana"
        } else {
            var text = ""

            if(counter == 1) {
                text += "Se ha completado " + counter.toString() + " hábito "
            } else {
                text += "Se han completado " + counter.toString() + " hábitos "
            }

            if(type == "weekly") {
                text += "esta semana"
            } else {
                text += "este mes"
            }
            graphTextView.text = text
        }

        val set = BarDataSet(entries, "BarDataSet")
        val l = binding.BarChart.legend;
        set.setColors(colors)
        l.isWordWrapEnabled = true
        l.yEntrySpace = 10f
        l.setCustom(legends)
        l.setEnabled(true)

        val data = BarData(set)
        val yAxis = binding.BarChart.axisLeft
        val xAxis = binding.BarChart.xAxis

        yAxis.typeface = Typeface.DEFAULT_BOLD
        yAxis.setTextSize(20f)
        yAxis.setAxisMinimum(0f)
        yAxis.setTextColor(Color.BLACK);
        yAxis.setGranularity(1f)

        xAxis.setTextSize(20f); // set the text size
        xAxis.setTextColor(Color.BLACK)
        xAxis.setEnabled(false)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        //xAxis.setDrawGridLines(false)
        binding.BarChart.axisRight.isEnabled = false
        binding.BarChart.data = data
        binding.BarChart.setFitBars(true)
        binding.BarChart.invalidate()
        binding.BarChart.animateY(2000)
    }
}