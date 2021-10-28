package com.example.proyecto

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class Pantalla_Vasos : AppCompatActivity() {

    private lateinit var habitManager: HabitManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_vasos)
        title = "Vasos de agua"
        habitManager = HabitManager(this)

        var alertTimes = ArrayList<String>()
        alertTimes.add("10:00")
        alertTimes.add("15:00")
        alertTimes.add("23:00")

        habitManager.addDailyHabit(getString(R.string.WATER), 5, alertTimes)

        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        updateViews()

        addButton.setOnClickListener {
            addHabit()
        }

        deleteButton.setOnClickListener {
            deleteHabit()
        }
    }

    private fun updateViews() {
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        addButton.setBackgroundColor(Color.BLUE)
        deleteButton.setBackgroundColor(Color.RED)

        val habit = habitManager.getHabit(getString(R.string.WATER))

        if(habit.isActive == 1) {
            var status = "El habito se encuentra activo"
            addButton.text = "Actualizar"
            deleteButton.isEnabled = true
            timesPerDayTextNumber.text = habit.timesPerDay.toString()

            if(habit.alertTimes != null) {
                status += " en los siguientes horarios: "

                for(time in habit.alertTimes!!) {
                    status += time + " "
                }
            } else {
                status += " sin un horario en específico"
            }

            statusTextView.text = status

        } else {
            statusTextView.text = "El hábito no esta activo"
            addButton.text = "Agregar"
            deleteButton.isEnabled = false
            timesPerDayTextNumber.text = "0"
        }
    }


    private fun addHabit() {

    }

    private fun deleteHabit() {

    }
}