package com.example.proyecto

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.proyecto.databinding.ActivityPantallaHabitoFaltanteBinding
import com.example.proyecto.databinding.ActivityPantallaVasosBinding

class Pantalla_Vasos : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var habitManager: HabitManager

    var counter = 0
    //        dropdown
    private lateinit var binding: ActivityPantallaVasosBinding
//        dropdown

    override fun onCreate(savedInstanceState: Bundle?) {

        //        dropdown
        binding = ActivityPantallaVasosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val valores = resources.getStringArray(R.array.country_location)
        val adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            valores
        )
        with(binding.autoCompleteTextView4){
            setAdapter(adapter)
            onItemClickListener = this@Pantalla_Vasos
        }
//        dropdown


        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pantalla_vasos)
        title = "Vasos de agua"
        habitManager = HabitManager(this)
        val habit = habitManager.getHabit(getString(R.string.Vasos_de_agua))

        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val addCounter = findViewById<Button>(R.id.sumar)
        val subsCounter = findViewById<Button>(R.id.restar)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)

        updateViews()

        addButton.setOnClickListener {
            addHabit()
        }

        deleteButton.setOnClickListener {
            deleteHabit()
        }

        addCounter.setOnClickListener {
            if(habitManager.isHabitActive(getString(R.string.Vasos_de_agua))) {
                counter  += 1
                counterTextView.text = counter.toString()
                updateCounterData()
            }
        }

        subsCounter.setOnClickListener {
            if(habitManager.isHabitActive(getString(R.string.Vasos_de_agua))) {
                if(counter <= 0){
                    Toast.makeText(this, "Los vasos consumidos deben ser igual o mayor a cero", Toast.LENGTH_SHORT).show()
                }
                else{
                    counter -= 1
                    counterTextView.text = counter.toString()
                }
                updateCounterData()
            }
        }
    }


    private fun updateCounterData() {
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)
        habitManager.setCompleted(getString(R.string.Vasos_de_agua), counter);
        val habit = habitManager.getHabit(getString(R.string.Vasos_de_agua))

        if((habit.timesPerDay - counter) <= 0){
            leftTextView.text = "0"
        }
        else{
            leftTextView.text = (habit.timesPerDay - counter).toString()
        }
        completedTextView.text = counter.toString()
    }

    private fun updateViews() {
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        addButton.setBackgroundColor(Color.BLUE)
        deleteButton.setBackgroundColor(Color.RED)

        val habit = habitManager.getHabit(getString(R.string.Vasos_de_agua))

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
        
        counter = habit.completed
        completedTextView.text = counter.toString()
        leftTextView.text = (habit.timesPerDay - habit.completed).toString()
        counterTextView.text = counter.toString()
    }


    private fun addHabit() {
        val addButton = findViewById<Button>(R.id.addButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        var timesPerDay = 0
        val frequency = "daily"
        var alertTimes = ArrayList<String>()
        var daysOfTheWeek = ArrayList<String>()
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        alertTimes.add("11:00")
        daysOfTheWeek.add("monday")
        daysOfTheWeek.add("wednesday")

        if(timesPerDayTextNumber.text.toString().length > 0) {
            timesPerDay = timesPerDayTextNumber.text.toString().toInt()

        } else {
            Toast.makeText(this, "El número de hábitos se encuentra vacio", Toast.LENGTH_SHORT).show()
            return
        }

        var success = false

        if(frequency == "daily") {
            success = habitManager.addDailyHabit(getString(R.string.Vasos_de_agua), timesPerDay, alertTimes)

            if(!success) {
                Toast.makeText(this, "Ocurrió un error al agregar el hábito diario", Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            success = habitManager.addWeeklyHabit(getString(R.string.Vasos_de_agua), timesPerDay, alertTimes, daysOfTheWeek)

            if(!success) {
                Toast.makeText(this, "Ocurrió un error al agregar el hábito semanal", Toast.LENGTH_SHORT).show()
                return
            }
        }

        counterTextView.text = "0"

        if(addButton.text == "Actualizar") {
            Toast.makeText(this, "Se ha actualizado el hábito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Se ha agregado el hábito", Toast.LENGTH_SHORT).show()
        }
        updateViews()
    }

    private fun deleteHabit() {
        val success = habitManager.deleteHabit(getString(R.string.Vasos_de_agua))

        if(success) {
            Toast.makeText(this, "Hábito eliminado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ocurrió un error al eliminar el hábito", Toast.LENGTH_SHORT).show()
        }
        updateViews()
    }
    //dropdown
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(this@Pantalla_Vasos, item, Toast.LENGTH_SHORT).show()
        val intent = Intent(this,pantalla_habito_faltante::class.java)
        startActivity(intent)
    }
    //dropdown

}