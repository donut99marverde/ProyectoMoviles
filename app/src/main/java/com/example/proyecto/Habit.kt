package com.example.proyecto

import java.util.*
import kotlin.collections.ArrayList

class Habit {
    var category : String = ""
    var frequency : String = ""
    var timesPerDay : Int = 1
    var daysOfTheWeek : ArrayList<String>
    var isActive: Int = 0
    var completed: Int = 0

    constructor(category: String, frequency: String, timesPerDay: Int, daysOfTheWeek: ArrayList<String>, isActive: Int, completed: Int) {
        this.category = category
        this.frequency = frequency
        this.timesPerDay = timesPerDay
        this.daysOfTheWeek = daysOfTheWeek
        this.isActive = isActive
        this.completed = completed
    }
}