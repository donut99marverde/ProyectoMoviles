package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.databinding.ActivityPantallaHabitoRecordsBinding
import java.time.LocalDate
import java.util.*

class PantallaRecords : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaHabitoRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaHabitoRecordsBinding.inflate(layoutInflater)
        title = "Historial"
        val habitManager = HabitManager(this)
        val records = habitManager.getRecords()
        var list = mutableListOf<ListElement>()
        val numberOfHabitsTextView = binding.numberOfHabitsTextView
        setContentView(binding.root)

        for(record in records) {
            list.add(ListElement(record.category, getDateAsString(record.completedDate), "activa", getIcon(record.category)))
        }

        if(list.size == 0) {
            numberOfHabitsTextView.text = "No has completado un hábito"
        } else {
            numberOfHabitsTextView.text = "Se han completados " + records.size.toString()
        }

        val adapter = RecordAdapter(list)
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

    private fun getDateAsString(date: Date) : String {
        val cal = Calendar.getInstance()
        cal.setTime(date)

        val day = cal.get(Calendar.DAY_OF_MONTH).toString()
        val month = getMonthAsString(cal.get(Calendar.MONTH))
        val year = cal.get(Calendar.YEAR).toString()
        return day + " - " + month + " - " + year
    }

    private fun getMonthAsString(month: Int) : String {
        when(month) {
            0 -> return "Enero"
            1 -> return "Febrero"
            2 -> return "Marzo"
            3 -> return "Abril"
            4 -> return "Mayo"
            5 -> return "Junio"
            6 -> return "Julio"
            7 -> return "Agosto"
            8 -> return "Septiembre"
            9 -> return "Octubre"
            10 -> return "Noviembre"
            else -> return "Diciembre"
        }
    }
}