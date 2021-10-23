package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        val context = this
        context.deleteDatabase("Habits")
        val habitManager = HabitManager(context)
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
    }
}