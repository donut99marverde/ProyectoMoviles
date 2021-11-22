package com.example.proyecto

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class HabitManager (context: Context) {

    var db = DBManager(context)

    fun addDailyHabit(category: String, timesPerDay: Int, alertTimes: ArrayList<String>?) : Boolean {
        deleteHabit(category)
        val daysOfTheWeek = ArrayList<String>()
        daysOfTheWeek.add("monday")
        daysOfTheWeek.add("tuesday")
        daysOfTheWeek.add("wednesday")
        daysOfTheWeek.add("thursday")
        daysOfTheWeek.add("friday")
        daysOfTheWeek.add("saturday")
        daysOfTheWeek.add("sunday")

        val habit = Habit(category, "daily", timesPerDay, daysOfTheWeek, alertTimes, 1)
        return db.addHabit(habit)
    }

    fun addWeeklyHabit(category: String, timesPerDay: Int, alertTimes: ArrayList<String>, daysOfTheWeek: ArrayList<String>) : Boolean {
        deleteHabit(category)
        val habit = Habit(category, "weekly", timesPerDay, daysOfTheWeek, alertTimes, 1)
        return db.addHabit(habit)
    }

    fun deleteHabit(category: String) : Boolean{
        return db.deleteHabit(category)
    }

    fun setCompleted(category: String, completedToday: Int) : Boolean {
        return db.updateCompleted(category, completedToday)
    }

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

    fun getWeeklyStats() : ArrayList<Stat> {
        return db.weeklyStats()
    }

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
        print(" alert times = ")

        if(habit.alertTimes != null) {
            print("[")
            for (alert in habit.alertTimes!!) {
                print(alert)
                print(",")
            }
            print("] ")
        } else {
            print(" no alert times ")
        }

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