package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        context.deleteDatabase("Habits")
        val habitManager = HabitManager(context)
        var alertTimes = ArrayList<String>()
        alertTimes.add("10:00")
        alertTimes.add("15:00")
        alertTimes.add("23:00")

        habitManager.addDailyHabit(getString(R.string.Vasos_de_agua), 7, alertTimes)
        habitManager.setCompleted(getString(R.string.Vasos_de_agua), 0)

        habitManager.addDailyHabit(getString(R.string.Meditacion), 3, alertTimes)
        habitManager.setCompleted(getString(R.string.Meditacion), 0)
        /*
        var alertTimes = ArrayList<String>()
        var daysOfTheWeek = ArrayList<String>()
        alertTimes.add("19:00")
        alertTimes.add("21:07")
        alertTimes.add("07:35")
        alertTimes.add("17:23")

        daysOfTheWeek.add("monday")
        daysOfTheWeek.add("wednesday")
        daysOfTheWeek.add("friday")

        habitManager.addDailyHabit("sleep", 7, null)
        habitManager.addDailyHabit("exercise", 7, null)
        habitManager.addWeeklyHabit("meditation", 3, alertTimes, daysOfTheWeek)

        var todayHabits = habitManager.todayHabits()

        for (habit in todayHabits) {
            habitManager.printHabitObj(habit)
        }

        habitManager.deleteHabit("exercise")
        println(" ")
        todayHabits = habitManager.todayHabits()

        for (habit in todayHabits) {
            habitManager.printHabitObj(habit)
        }*/


        println(habitManager.isHabitActive(getString(R.string.Vasos_de_agua)))

        val habit5Button = findViewById<Button>(R.id.button_scroll_5)
        val habit6Button = findViewById<Button>(R.id.button_scroll_6)
        val habit7Button = findViewById<Button>(R.id.button_scroll_7);
        val habitoHOY = findViewById<Button>(R.id.button_habitoHoy);

        habit5Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Raciones_Frutas_Vegetales::class.java)
            startActivity(intent)
        }

        habit6Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Meditacion::class.java)
            startActivity(intent)
        }

        habit7Button.setOnClickListener(){
            val intent = Intent(this,Pantalla_Vasos::class.java)
            startActivity(intent)
        }

        habitoHOY.setOnClickListener(){
            val intent = Intent(this,pantalla_habito_faltante::class.java)
            startActivity(intent)
        }

    }
}