package com.example.proyecto

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

class HabitManager (context: Context) {

    var db = DBManager(context)

    fun addDailyHabit(category: String, timesPerDay: Int) : Boolean {
        deleteHabit(category)
        val daysOfTheWeek = ArrayList<String>()
        daysOfTheWeek.add("monday")
        daysOfTheWeek.add("tuesday")
        daysOfTheWeek.add("wednesday")
        daysOfTheWeek.add("thursday")
        daysOfTheWeek.add("friday")
        daysOfTheWeek.add("saturday")
        daysOfTheWeek.add("sunday")

        val habit = Habit(category, "daily", timesPerDay, daysOfTheWeek, 1, 0)
        return db.addHabit(habit)
    }

    fun addWeeklyHabit(category: String, timesPerDay: Int, daysOfTheWeek: ArrayList<String>) : Boolean {
        deleteHabit(category)
        val habit = Habit(category, "weekly", timesPerDay, daysOfTheWeek, 1, 0)
        return db.addHabit(habit)
    }

    fun deleteHabit(category: String) : Boolean{
        return db.deleteHabit(category)
    }

    fun setCompleted(category: String, completedToday: Int) : Boolean {
        return db.updateCompleted(category, completedToday)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun todayHabits() : ArrayList<Habit>{
        return db.todayHabits()
    }

    fun isHabitActive(category: String): Boolean {
        return db.isHabitActive(category)
    }

    fun getHabit(category: String): Habit {
        return db.getHabit(category)
    }

    fun getRecords() : ArrayList<Record> {
        return db.getRecords()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getWeeklyStats() : ArrayList<Stat> {
        return db.weeklyStats()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getMonthlyStats() : ArrayList<Stat> {
        return db.monthlyStats()
    }

    fun printTables() {
        db.printInfoFromAllTables()
    }

    fun printHabitObj(habit: Habit)  {
        print("category = ")
        print(habit.category)
        print(" frequency = ")
        print(habit.frequency)
        print(" times per day = ")
        print(habit.timesPerDay)

        print("weekdays = [")
        for(weekday in habit.daysOfTheWeek) {
            print(weekday)
            print(",")
        }
        print("]")
        print(" completed = ")
        println(habit.completed)
    }
}