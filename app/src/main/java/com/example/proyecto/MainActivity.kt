package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        context.deleteDatabase("Habits")
        val habitManager = HabitManager(context)
        val habit4Button = findViewById<Button>(R.id.button_scroll_4)
        val habit5Button = findViewById<Button>(R.id.button_scroll_5)
        val habit6Button = findViewById<Button>(R.id.button_scroll_6)
        val habit7Button = findViewById<Button>(R.id.button_scroll_7);
        val habitoHOY = findViewById<Button>(R.id.button_habitoHoy)
        val habitoGraficas = findViewById<Button>(R.id.graficasButton)
        val habitoRecords = findViewById<Button>(R.id.habitoRecordsButton)

        habit4Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Hábito::class.java)
            startActivity(intent)
        }

        //raciones de frutas y verduras
        habit5Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Hábito::class.java)
            intent.putExtra("category", getString(R.string.Raciones_de_frutas_y_vegetales))
            intent.putExtra("timePerDayTextView", "Número de raciones al día")
            intent.putExtra("completedLabel", "Raciones consumidas")
            intent.putExtra("leftLabel", "Raciones restantes")
            startActivity(intent)
        }

        //meditación
        habit6Button.setOnClickListener {
            val intent = Intent(this, Pantalla_Hábito::class.java)
            intent.putExtra("category", getString(R.string.Meditacion))
            intent.putExtra("timePerDayTextView", "Número de meditaciones al día")
            intent.putExtra("completedLabel", "Meditaciones realizadas")
            intent.putExtra("leftLabel", "Meditaciones restantes")
            startActivity(intent)
        }

        //vasos de agus
        habit7Button.setOnClickListener(){
            val intent = Intent(this,Pantalla_Hábito::class.java)
            intent.putExtra("category", getString(R.string.Vasos_de_agua))
            intent.putExtra("timePerDayTextView", "Vasos de agua al día")
            intent.putExtra("completedLabel", "Vasos de agua tomados")
            intent.putExtra("leftLabel", "Vasos de agua restantes")
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