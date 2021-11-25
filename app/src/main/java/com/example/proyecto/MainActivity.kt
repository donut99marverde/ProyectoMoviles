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
        //context.deleteDatabase("Habits")
        val habitManager = HabitManager(context)
        val habit5Button = findViewById<Button>(R.id.button_scroll_5)
        val habit6Button = findViewById<Button>(R.id.button_scroll_6)
        val habit7Button = findViewById<Button>(R.id.button_scroll_7);
        val habitoHOY = findViewById<Button>(R.id.button_habitoHoy)
        val habitoGraficas = findViewById<Button>(R.id.graficasButton)
        val habitoRecords = findViewById<Button>(R.id.habitoRecordsButton)

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

        habitoGraficas.setOnClickListener {
            val intent = Intent(this, Pantalla_Graficas::class.java)
            startActivity(intent)
        }

        habitoRecords.setOnClickListener {
            val intent = Intent(this, PantallaRecords::class.java)
            startActivity(intent)
        }

    }
}