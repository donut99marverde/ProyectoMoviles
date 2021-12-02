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
        val habit1Button = findViewById<Button>(R.id.button_scroll_1)
        val habit2Button = findViewById<Button>(R.id.button_scroll_2)
        val habit3Button = findViewById<Button>(R.id.button_scroll_3)
        val habit4Button = findViewById<Button>(R.id.button_scroll_4)
        val habit5Button = findViewById<Button>(R.id.button_scroll_5)
        val habit6Button = findViewById<Button>(R.id.button_scroll_6)
        val habit7Button = findViewById<Button>(R.id.button_scroll_7)
        val habit8Button = findViewById<Button>(R.id.button_scroll_8)
        val habitoHOY = findViewById<Button>(R.id.button_habitoHoy)
        val habitoGraficas = findViewById<Button>(R.id.graficasButton)
        val habitoRecords = findViewById<Button>(R.id.habitoRecordsButton)

        setProgressBar()

        //Ejercicio
        habit1Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Rutina_de_ejercicio))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.rutina_ejercicio_1)
            intent.putExtra("timePerDayTextView", "Número de rutinas de ejercicio al dia: ")
            intent.putExtra("completedLabel", "Rutinas completadas: ")
            intent.putExtra("leftLabel", "Rutinas restantes: ")
            startActivity(intent)
        }

        //horas antes de dormir sin celular
        habit2Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Horas_antes_de_dormir_sin_celular))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.notifications_paused_2)
            intent.putExtra("timePerDayTextView", "Número de horas donde no se usara el celular antes de dormir: ")
            intent.putExtra("completedLabel", "Horas sin usar: ")
            intent.putExtra("leftLabel", "Horas restantes: ")
            startActivity(intent)
        }

        //Comidas completas
        habit3Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Comidas_completas))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.comida_completa_3)
            intent.putExtra("timePerDayTextView", "Número de comidas completas al día: ")
            intent.putExtra("completedLabel", "Comidas completas consumidas: ")
            intent.putExtra("leftLabel", "Comidas completas restantes: ")
            startActivity(intent)
        }

        //Breaks
        habit4Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Breaks))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.break_4)
            intent.putExtra("timePerDayTextView", "Número de breaks al día: ")
            intent.putExtra("completedLabel", "Breaks tomados: ")
            intent.putExtra("leftLabel", "Breaks restantes: ")
            startActivity(intent)
        }

        //Raciones de frutas y verduras
        habit5Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Raciones_de_frutas_y_vegetales))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.fruit_vegetable_5)
            intent.putExtra("timePerDayTextView", "Número de raciones al día: ")
            intent.putExtra("completedLabel", "Raciones consumidas: ")
            intent.putExtra("leftLabel", "Raciones restantes: ")
            startActivity(intent)
        }

        //Meditación
        habit6Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Meditacion))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.meditar_6)
            intent.putExtra("timePerDayTextView", "Número de meditaciones al día: ")
            intent.putExtra("completedLabel", "Meditaciones realizadas: ")
            intent.putExtra("leftLabel", "Meditaciones restantes: ")
            startActivity(intent)
        }

        //Vasos de agua
        habit7Button.setOnClickListener(){
            val intent = Intent(this,Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string.Vasos_de_agua))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.water_glass_7)
            intent.putExtra("timePerDayTextView", "Vasos de agua al día: ")
            intent.putExtra("completedLabel", "Vasos de agua tomados: ")
            intent.putExtra("leftLabel", "Vasos de agua restantes: ")
            startActivity(intent)
        }

        //Horas de sueño
        habit8Button.setOnClickListener(){
            val intent = Intent(this,Pantalla_Habito::class.java)
            intent.putExtra("category", getString(R.string._8_horas_de_sue_o))
            intent.putExtra("habitMessage", "Mensaje personalizado sobre el hábito")
            intent.putExtra("icon", R.drawable.horas_suenio_8)
            intent.putExtra("timePerDayTextView", "Horas de sueño: ")
            intent.putExtra("completedLabel", "Horas de sueño dormias: ")
            intent.putExtra("leftLabel", "Horas de sueño restantes: ")
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