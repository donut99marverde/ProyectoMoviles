package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto.databinding.ActivityPantallaHabitoFaltanteBinding
import androidx.recyclerview.widget.LinearLayoutManager

class pantalla_habito_faltante : AppCompatActivity() {

    private lateinit var  binding: ActivityPantallaHabitoFaltanteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityPantallaHabitoFaltanteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        var doHabit = mutableListOf<ListElement>(
            ListElement("Rutina de Ejericicio", "0/1", "activa", R.drawable.rutina_ejercicio_1),
            ListElement("Notificaciones", "0/1", "activa", R.drawable.notifications_paused_2),
            ListElement("Comidas Completas", "1/3", "activa", R.drawable.comida_completa_3),
            ListElement("Breaks", "3/4", "activa", R.drawable.break_4),
            ListElement("Frutas y Vegetales", "1/5", "activa", R.drawable.fruit_vegetable_5),
            ListElement("Meditación", "0/1", "activa", R.drawable.meditar_6),
            ListElement("Vasos de agua", "1/7", "activa", R.drawable.water_glass_7),
            ListElement("Horas de sueño", "0/1", "activa", R.drawable.horas_suenio_8),
            ListElement("Pasos", "0 /1", "activa", R.drawable.pasos_9)
        )

        val adapter = habitoAdapter(doHabit)
        binding.RVhabitos.adapter = adapter
        binding.RVhabitos.layoutManager = LinearLayoutManager(this)
    }
}