package com.example.proyecto

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Pantalla_Vasos : AppCompatActivity() {

    private lateinit var habitManager: HabitManager

    var contadorVasos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_vasos)
        title = "Vasos de agua"
        habitManager = HabitManager(this)
        val habit = habitManager.getHabit(getString(R.string.WATER))

        var alertTimes = ArrayList<String>()
        alertTimes.add("10:00")
        alertTimes.add("15:00")
        alertTimes.add("23:00")

        habitManager.addDailyHabit(getString(R.string.WATER), 7, alertTimes)
        habitManager.setCompleted("water", 0)

        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val addCounterVaso = findViewById<Button>(R.id.sumarVaso)
        val subsCounterVaso = findViewById<Button>(R.id.restarVaso)
        val vasos = findViewById<TextView>(R.id.numVasos)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)

        updateViews()


        addButton.setOnClickListener {
            addHabit()
        }

        deleteButton.setOnClickListener {
            deleteHabit()
        }

        addCounterVaso.setOnClickListener {
            contadorVasos += 1
            vasos.text = contadorVasos.toString()
            actualizarDatosContador()
        }

        subsCounterVaso.setOnClickListener {
            if(contadorVasos <= 0){
                Toast.makeText(this, "Los vasos consumidos deben ser igual o mayor a cero", Toast.LENGTH_SHORT).show()
            }
            else{
                contadorVasos -= 1
                vasos.text = contadorVasos.toString()
            }
            actualizarDatosContador()
        }



    }


    private fun actualizarDatosContador() {
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)
        val vasos = findViewById<TextView>(R.id.numVasos)
        val habit = habitManager.getHabit(getString(R.string.WATER))

        if((habit.timesPerDay - contadorVasos) <= 0){
            leftTextView.text = "0"
        }
        else{
            leftTextView.text = (habit.timesPerDay - contadorVasos).toString()
        }
        completedTextView.text = contadorVasos.toString()
    }

    private fun updateViews() {
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)
        val vasos = findViewById<TextView>(R.id.numVasos)
        addButton.setBackgroundColor(Color.BLUE)
        deleteButton.setBackgroundColor(Color.RED)

        val habit = habitManager.getHabit(getString(R.string.WATER))

        habitManager.printHabitObj(habit)

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
            completedTextView.text = habit.completed.toString()

        } else {
            statusTextView.text = "El hábito no esta activo"
            addButton.text = "Agregar"
            deleteButton.isEnabled = false
            timesPerDayTextNumber.text = "0"
            completedTextView.text = "0"
        }


        if(habit.completed < habit.timesPerDay) {
            leftTextView.text = (habit.timesPerDay - habit.completed).toString()
        } else {
            leftTextView.text = "0"
        }

        contadorVasos = 0
        vasos.text = contadorVasos.toString()
    }


    private fun addHabit() {
        val addButton = findViewById<Button>(R.id.addButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val vasos = findViewById<TextView>(R.id.numVasos)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)

        var timesPerDay = 0
        val frequency = "daily"
        var alertTimes = ArrayList<String>()
        var daysOfTheWeek = ArrayList<String>()
        alertTimes.add("11:00")
        daysOfTheWeek.add("monday")
        daysOfTheWeek.add("wednesday")

        if(timesPerDayTextNumber.text.toString().length > 0) {
            timesPerDay = timesPerDayTextNumber.text.toString().toInt()

        } else {
            Toast.makeText(this, "El número de hábitos se encuentra vacio", Toast.LENGTH_SHORT).show()
        }

        var success = false

        if(frequency == "daily") {
            success = habitManager.addDailyHabit("water", timesPerDay, alertTimes)

            if(!success) {
                Toast.makeText(this, "Ocurrió un error al agregar el hábito diario", Toast.LENGTH_SHORT).show()
            }
        } else {
            success = habitManager.addWeeklyHabit("water", timesPerDay, alertTimes, daysOfTheWeek)

            if(!success) {
                Toast.makeText(this, "Ocurrió un error al agregar el hábito semanal", Toast.LENGTH_SHORT).show()
            }
        }

        if(addButton.text == "Actualizar") {
            Toast.makeText(this, "Se ha actualizado el hábito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Se ha agregado el hábito", Toast.LENGTH_SHORT).show()
        }
        updateViews()
    }

    private fun deleteHabit() {
        val success = habitManager.deleteHabit("water")

        if(success) {
            Toast.makeText(this, "Hábito eliminado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ocurrió un error al eliminar el hábito", Toast.LENGTH_SHORT).show()
        }
        updateViews()
    }

}