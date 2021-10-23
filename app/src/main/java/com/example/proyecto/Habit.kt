package com.example.proyecto

import java.util.*
import kotlin.collections.ArrayList

class Habit {
    var category : String = ""
    var frequency : String = ""
    var timesPerDay : Int = 1
    var daysOfTheWeek : ArrayList<String>
    var alertTimes : ArrayList<String>?
    var isActive: Int = 0

    constructor(category: String, frequency: String, timesPerDay: Int, daysOfTheWeek: ArrayList<String>, alertTimes: ArrayList<String>?, isActive: Int) {
        this.category = category
        this.frequency = frequency
        this.timesPerDay = timesPerDay
        this.daysOfTheWeek = daysOfTheWeek
        this.alertTimes = alertTimes
        this.isActive = isActive
    }
}