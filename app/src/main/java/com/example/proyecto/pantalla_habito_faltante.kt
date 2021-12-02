package com.example.proyecto

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.proyecto.databinding.ActivityPantallaHabitoFaltanteBinding
import androidx.recyclerview.widget.LinearLayoutManager

class pantalla_habito_faltante : AppCompatActivity() {

    private lateinit var  binding: ActivityPantallaHabitoFaltanteBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityPantallaHabitoFaltanteBinding.inflate(layoutInflater)
        val habitManager = HabitManager(this)
        val activeHabits = habitManager.todayHabits()
        var today = mutableListOf<ListElement>()
        val numberOfHabitsTextView = binding.numberOfHabitsTextView
        setContentView(binding.root)

        for(habit in activeHabits) {
            habitManager.printHabitObj(habit)
            today.add(ListElement(habit.category, habit.completed.toString() + "/" + habit.timesPerDay.toString(), "activa", getIcon(habit.category)))
        }

        if(today.size == 0) {
            numberOfHabitsTextView.text = "No hay ningún hábito programado para hoy"
        } else if(today.size == 1) {
            numberOfHabitsTextView.text = "Se tiene programado " + today.size.toString() + " hábito para hoy"
        } else {
            numberOfHabitsTextView.text = "Se tienen programados " + today.size.toString() + " hábitos para hoy"
        }

        val adapter = habitoAdapter(today)
        binding.RVhabitos.adapter = adapter
        binding.RVhabitos.layoutManager = LinearLayoutManager(this)
    }

    private fun getIcon(category: String) : Int{
        when(category) {
            getString(R.string.Rutina_de_ejercicio) -> return R.drawable.rutina_ejercicio_1
            getString(R.string.Horas_antes_de_dormir_sin_celular) -> return R.drawable.notifications_paused_2
            getString(R.string.Comidas_completas) -> return R.drawable.comida_completa_3
            getString(R.string.Breaks) -> return R.drawable.break_4
            getString(R.string.Raciones_de_frutas_y_vegetales) -> return R.drawable.fruit_vegetable_5
            getString(R.string.Meditacion) -> return R.drawable.meditar_6
            getString(R.string.Vasos_de_agua) -> return R.drawable.water_glass_7
            getString(R.string.Horas_de_sueño) -> return R.drawable.horas_suenio_8
            else -> {
                return R.drawable.pasos_9
            }
        }
    }
}