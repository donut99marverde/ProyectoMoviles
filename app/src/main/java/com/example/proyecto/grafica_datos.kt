package com.example.proyecto


import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto.databinding.GraficaHabitosBinding
import com.example.proyecto.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*

import java.security.KeyStore
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class grafica_datos : AppCompatActivity() {

    private lateinit var binding: GraficaHabitosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GraficaHabitosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBArChart2()
    }


    fun setBarchart() {

        /* var grafdata  = ArrayList<BarEntry>()




         var NoOfEmp =  ArrayList<BarEntry>()

         NoOfEmp.add(BarEntry(945f,0f))
         NoOfEmp.add(BarEntry(1040f,1f))
         NoOfEmp.add(BarEntry(1133f,2f))
         NoOfEmp.add(BarEntry(1240f,3f))
         NoOfEmp.add(BarEntry(1369f,4f))
         NoOfEmp.add(BarEntry(1487f,5f))
         NoOfEmp.add(BarEntry(1501f,6f))
         NoOfEmp.add(BarEntry(1645f,7f))
         NoOfEmp.add(BarEntry(1578f,8f))
         NoOfEmp.add(BarEntry(1695f,9f))


         val year = ArrayList<IBarDataSet>()

         year.add(BarDataSet(NoOfEmp,"Data Set"))


         var bardataset : BarDataSet = BarDataSet(NoOfEmp,"No of Employee")
         binding.lineChart.animateY(5000)
         val data = BarData(year[0], bardataset)

         val data2 = BarData(year)
         binding.lineChart.data = data*/



    }


    fun setBArChart2() {

        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))

        // gap of 2f
        // gap of 2f
        entries.add(BarEntry(5f, 70f))
        entries.add(BarEntry(6f, 60f))

        val set = BarDataSet(entries, "BarDataSet")
        var colorList = mutableListOf<Int>()
        ColorTemplate.JOYFUL_COLORS.forEach {colorList.add(it)  }

        // var colorList = mutableListOf<Int>(ColorTemplate.JOYFUL_COLORS.forEach {  })
        set.setColors(colorList)
        // set.setColors(ColorTemplate.COLORFUL_COLORS)

        var data = BarData(set)
        binding.BarChart.data = data
        binding.BarChart.setFitBars(true)
        binding.BarChart.invalidate()
        binding.BarChart.animateY(2000)



    }


}