package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.widget.ProgressBar
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Hábitos"
        //val context = this
        //context.deleteDatabase("Habits")
        val habit4Button = findViewById<Button>(R.id.button_scroll_4)
        val habit5Button = findViewById<Button>(R.id.button_scroll_5)
        val habit6Button = findViewById<Button>(R.id.button_scroll_6)
        val habit7Button = findViewById<Button>(R.id.button_scroll_7);
        val habitoHOY = findViewById<Button>(R.id.button_habitoHoy)
        val habitoGraficas = findViewById<Button>(R.id.graficasButton)
        val habitoRecords = findViewById<Button>(R.id.habitoRecordsButton)

        setProgressBar()


        habit4Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            startActivity(intent)
        }

        //raciones de frutas y verduras
        habit5Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Raciones_de_frutas_y_vegetales))
            intent.putExtra("timePerDayTextView", "Número de raciones al día: ")
            intent.putExtra("completedLabel", "Raciones consumidas: ")
            intent.putExtra("leftLabel", "Raciones restantes: ")
            startActivity(intent)
        }

        //meditación
        habit6Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Meditacion))
            intent.putExtra("timePerDayTextView", "Número de meditaciones al día: ")
            intent.putExtra("completedLabel", "Meditaciones realizadas:")
            intent.putExtra("leftLabel", "Meditaciones restantes: ")
            startActivity(intent)
        }

        //vasos de agus
        habit7Button.setOnClickListener(){
            val intent = Intent(this,Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Vasos_de_agua))
            intent.putExtra("timePerDayTextView", "Vasos de agua al día: ")
            intent.putExtra("completedLabel", "Vasos de agua tomados: ")
            intent.putExtra("leftLabel", "Vasos de agua restantes: ")
            startActivity(intent)
        }

        habitoHOY.setOnClickListener(){
            val intent = Intent(this,pantalla_habito_faltante::class.java)
            startActivity(intent)
        }

        habitoGraficas.setOnClickListener {
            val intent = Intent(this, Pantalla_Graficas::class.java)
            startActivity(intent)
        }

        habitoRecords.setOnClickListener {
            val intent = Intent(this, PantallaRecords::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onResume() {
        super.onResume()
        setProgressBar()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setProgressBar() {
        val habitManager = HabitManager(this)
        val todayHabits = habitManager.todayHabits()
        val numberOfCategoriesWithARecordToday = habitManager.numberOfCategoriesWithARecordToday()

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.min = 0
        progressBar.max = 100
        progressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)

        if(todayHabits.size == 0) {
            progressBar.progress = 0
        } else {
            progressBar.progress =  (numberOfCategoriesWithARecordToday.toFloat() / todayHabits.size.toFloat() * 100.0).toInt()
        }

        if(progressBar.progress >= 80) {
            progressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)
        } else if(progressBar.progress >= 40) {
            progressBar.progressTintList = ColorStateList.valueOf(Color.YELLOW)
        } else {
            progressBar.progressTintList = ColorStateList.valueOf(Color.RED)
        }
    }
}