package com.example.proyecto

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.proyecto.databinding.ActivityPantallaHabitoBinding

class Pantalla_Habito : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var habitManager: HabitManager
    private val options = arrayOf("Lunes","Martes","Miercoles")
    private var defaultPosition = 0
    private lateinit var category : String
    var counter = 0
    private lateinit var binding: ActivityPantallaHabitoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPantallaHabitoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val valores = resources.getStringArray(R.array.country_location)
        val adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            valores
        )

        val intent = intent
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val timesPerDayTextView = findViewById<TextView>(R.id.timesPerDayTextView)
        val completedLabel = findViewById<TextView>(R.id.completedLabel)
        val leftLabel = findViewById<TextView>(R.id.leftLabel)
        val addCounter = findViewById<Button>(R.id.sumar)
        val subsCounter = findViewById<Button>(R.id.restar)
        super.onCreate(savedInstanceState)

        category = intent.getStringExtra("category")!!
        title = category
        timesPerDayTextView.text = intent.getStringExtra("timePerDayTextView")!!
        completedLabel.text = intent.getStringExtra("completedLabel")
        leftLabel.text = intent.getStringExtra("leftLabel")
        habitManager = HabitManager(this)

        with(binding.autoCompleteTextView4){
            setAdapter(adapter)
            onItemClickListener = this@Pantalla_Habito
        }


        updateViews()

        addButton.setOnClickListener {
            addHabit()
        }

        deleteButton.setOnClickListener {
            deleteHabit()
        }

        addCounter.setOnClickListener {
            incrementCounter()
        }

        subsCounter.setOnClickListener {
            substractCounter()
        }
    }

    private fun incrementCounter() {
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        if(habitManager.isHabitActive(category)) {
            counter  += 1
            counterTextView.text = counter.toString()
            //actualizar en DB
            habitManager.setCompleted(category, counter)
            updateViews()
        }
    }

    private fun substractCounter() {
        if(habitManager.isHabitActive(category) && counter > 0) {
            counter -= 1
            //actualizar en DB
            habitManager.setCompleted(category, counter)
            updateViews()
        }
    }

    private fun updateViews() {
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val completedTextView = findViewById<TextView>(R.id.completedTextView)
        val leftTextView = findViewById<TextView>(R.id.leftTextView)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)

        addButton.setBackgroundColor(Color.parseColor("#2196f3"))
        statusTextView.setPadding(20, 20, 20, 20)

        val habit = habitManager.getHabit(category)

        if(habit.isActive == 1) {
            var status = "El habito se encuentra activo "
            addButton.text = "Actualizar"
            deleteButton.isEnabled = true
            timesPerDayTextNumber.text = habit.timesPerDay.toString()

            if(habit.frequency == "daily") {
                status += "todos los dias"
            } else {
                status += "los siguientes dias"
                for(weekday in habit.daysOfTheWeek) {
                    status += weekday
                }
            }

            statusTextView.text = status
            statusTextView.setBackgroundColor(Color.parseColor("#c8e6c9"))
            deleteButton.setBackgroundColor(Color.parseColor("#c63f17"))
            completedTextView.text = habit.completed.toString()

        } else {
            statusTextView.text = "El hábito no esta activo"
            statusTextView.setBackgroundColor(Color.parseColor("#ff867c"))
            deleteButton.setBackgroundColor(Color.parseColor("#ef9a9a"))
            addButton.text = "Agregar"
            deleteButton.isEnabled = false
            timesPerDayTextNumber.text = "0"
            completedTextView.text = "0"
        }

        /*Actualizar completados, restantes y contador*/
        counter = habit.completed
        completedTextView.text = counter.toString()

        if((habit.timesPerDay - counter) <= 0){
            leftTextView.text = "0"
        } else {
            leftTextView.text = (habit.timesPerDay - habit.completed).toString()
        }
        counterTextView.text = counter.toString()
    }


    private fun addHabit() {
        val addButton = findViewById<Button>(R.id.addButton)
        val timesPerDayTextNumber = findViewById<TextView>(R.id.timesPerDayTextNumber)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        var timesPerDay : Int

        /*Si el habito es diario se pone 'daily' de lo contrario si es semanal se ponde 'weekly'*/
        val frequency = "daily"

        var daysOfTheWeek = ArrayList<String>()

        /*
        Agregar a daysOfTheWeek los dias de la semana en los que se hará el hábito si el hábito es semanal
         */
        daysOfTheWeek.add("Lunes")
        daysOfTheWeek.add("Martes")

        if(timesPerDayTextNumber.text.toString().isNotEmpty()) {
            timesPerDay = timesPerDayTextNumber.text.toString().toInt()
        } else {
            Toast.makeText(this, "El número de hábitos se encuentra vacio", Toast.LENGTH_SHORT).show()
            return
        }

        var success : Boolean

        if(frequency == "daily") {
            success = habitManager.addDailyHabit(category, timesPerDay)

            if(!success) {
                Toast.makeText(this, "Ocurrió un error al agregar el hábito diario", Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            success = habitManager.addWeeklyHabit(category, timesPerDay, daysOfTheWeek)

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
        Toast.makeText(this@Pantalla_Habito, item, Toast.LENGTH_SHORT).show()

        if(item == "Semanal")
        {
            val builderSingle = AlertDialog.Builder(this)
            builderSingle.setTitle("Select")
            builderSingle.setPositiveButton(getString(android.R.string.ok)) {dialog, _ -> dialog.dismiss()}
            builderSingle.setSingleChoiceItems(options, defaultPosition) {dialog, which ->
                defaultPosition = which
            }
            builderSingle.create()
        }


//        val intent = Intent(this,pantalla_habito_faltante::class.java)
//        startActivity(intent)
    }
    //dropdown

}