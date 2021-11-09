package com.example.proyecto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

val DATABASE_NAME = "Habits"
val ACTIVE_HABITS_TABLE = "ActiveHabits"
val DAYS_OF_THE_WEEK_TABLE = "DaysOfTheWeek"
val TIME_OF_THE_DAY_TABLE = "TimeOfTheDay"
val RECORDS_TABLE = "Records"
val TODAY_INFO_TABLE = "TodayInfo"

/*Active Habits columns*/
val ACTIVE_COL_HABIT_ID = "habitID"
val ACTIVE_COL_CATEGORY = "category"
val ACTIVE_COL_FREQUENCY = "frequency"
val ACTIVE_COL_TIMES_PER_DAY = "timesPerDay"
val ACTIVE_COL_IS_ACTIVE = "isActive"

/*Days of the week columns*/
val DAYS_COL_HABIT_ID = "habitID"
val DAYS_COL_ON_MONDAY = "monday"
val DAYS_COL_ON_TUESDAY = "tuesday"
val DAYS_COL_ON_WEDNESDAY = "wednesday"
val DAYS_COL_ON_THURSDAY = "thursday"
val DAYS_COL_ON_FRIDAY = "friday"
val DAYS_COL_ON_SATURDAY = "saturday"
val DAYS_COL_ON_SUNDAY = "sunday"

/*Time of the day columns*/
val TIME_COL_ID = "id"
val TIME_COL_HABIT_ID = "habitID"
val TIME_COL_HOUR = "hour"
val TIME_COL_MINUTES ="minutes"

/*TodayInfo columns*/
val TODAY_COL_HABIT_ID = "habitID"
val TODAY_COL_TIMES_COMPLETED = "timesCompleted"
val TODAY_COL_DATE = "date"

/*Records columns*/
val RECORDS_ID = "id"
val RECORDS_HABIT_ID= "habitID"
val RECORDS_COMPLETED_DATE = "completedDate"

class DBManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    val categories = listOf<String>(
        context.getString(R.string.Meditacion),
        context.getString(R.string.Rutina_de_ejercicio),
        context.getString(R.string.Horas_de_sueño),
        context.getString(R.string.Vasos_de_agua),
        context.getString(R.string.Comidas_completas),
        context.getString(R.string.Horas_antes_de_dormir_sin_celular),
        context.getString(R.string.Raciones_de_frutas_y_vegetales),
        context.getString(R.string.Breaks),
        context.getString(R.string.Pasos))



    override fun onCreate(p0: SQLiteDatabase?) {

        val createActiveHabitsTable = "CREATE TABLE " + ACTIVE_HABITS_TABLE + " (" +
                ACTIVE_COL_HABIT_ID + " INTEGER PRIMARY KEY, " +
                ACTIVE_COL_CATEGORY + " VARCHAR(50), " +
                ACTIVE_COL_FREQUENCY + " VARCHAR(50), " +
                ACTIVE_COL_TIMES_PER_DAY + " INTEGER, " +
                ACTIVE_COL_IS_ACTIVE + " INTEGER)"

        val createDaysOfTheWeekTable = "CREATE TABLE " + DAYS_OF_THE_WEEK_TABLE + " (" +
                DAYS_COL_HABIT_ID + " INTEGER PRIMARY KEY, " +
                DAYS_COL_ON_MONDAY + " INTEGER, " +
                DAYS_COL_ON_TUESDAY + " INTEGER, " +
                DAYS_COL_ON_WEDNESDAY + " INTEGER, " +
                DAYS_COL_ON_THURSDAY + " INTEGER, " +
                DAYS_COL_ON_FRIDAY + " INTEGER, " +
                DAYS_COL_ON_SATURDAY + " INTEGER, " +
                DAYS_COL_ON_SUNDAY + " INTEGER)"

        val createTimeOfTheDayTable = "CREATE TABLE " + TIME_OF_THE_DAY_TABLE + " (" +
                TIME_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIME_COL_HABIT_ID + " INTEGER, " +
                TIME_COL_HOUR + " VARCHAR(100), " +
                TIME_COL_MINUTES + " VARCHAR(100))"

        val createTodayInfoTable = "CREATE TABLE " + TODAY_INFO_TABLE + " (" +
                TODAY_COL_HABIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TODAY_COL_TIMES_COMPLETED + " INTEGER, " +
                TODAY_COL_DATE + " VARCHAR(100))"

        val createRecordsTable = "CREATE TABLE " + RECORDS_TABLE + " (" +
                RECORDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RECORDS_HABIT_ID + " INTEGER, " +
                RECORDS_COMPLETED_DATE + " VARCHAR(100))"

        p0?.execSQL(createActiveHabitsTable)
        p0?.execSQL(createDaysOfTheWeekTable)
        p0?.execSQL(createTimeOfTheDayTable)
        p0?.execSQL(createTodayInfoTable)
        p0?.execSQL(createRecordsTable)

        val cvHabits = contentValuesOf()
        val cvDaysOfTheWeek = contentValuesOf()
        val cvTodayInfo = contentValuesOf()

        for(category in categories) {
            cvHabits.put(ACTIVE_COL_CATEGORY, category)
            cvHabits.put(ACTIVE_COL_FREQUENCY, "daily")
            cvHabits.put(ACTIVE_COL_TIMES_PER_DAY, 0)
            cvHabits.put(ACTIVE_COL_IS_ACTIVE, 0)
            p0?.insert(ACTIVE_HABITS_TABLE, null, cvHabits)
        }

        for(category in categories) {
            val habitID = getHabitID(category, p0!!)
            cvDaysOfTheWeek.put(DAYS_COL_HABIT_ID, habitID)
            cvDaysOfTheWeek.put(DAYS_COL_ON_MONDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_TUESDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_WEDNESDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_THURSDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_FRIDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_SATURDAY, 0)
            cvDaysOfTheWeek.put(DAYS_COL_ON_SUNDAY, 0)
            p0.insert(DAYS_OF_THE_WEEK_TABLE, null, cvDaysOfTheWeek)
        }

        for(category in categories) {
            val habitID = getHabitID(category, p0!!)
            cvTodayInfo.put(TODAY_COL_HABIT_ID, habitID)
            cvTodayInfo.put(TODAY_COL_TIMES_COMPLETED, 0)
            val date = Date()
            cvTodayInfo.put(TODAY_COL_DATE, date.toString())
            p0.insert(TODAY_INFO_TABLE, null, cvTodayInfo)
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    private fun getHabitID(category: String, db: SQLiteDatabase): String {
        val query = "Select " + ACTIVE_COL_HABIT_ID + " FROM " + ACTIVE_HABITS_TABLE + " WHERE " + ACTIVE_COL_CATEGORY +
                " = " + "'$category'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        val id = cursor.getString(0).toString()
        cursor.close()
        return id
    }

    fun addHabit(habit: Habit) : Boolean {
        val db = this.writableDatabase

        val habitID = getHabitID(habit.category, db)

        if(!setHabitInfo(habit, habitID, db)) {
            return false
        }

        if(!setWeekdays(habit, habitID,  db)) {
            return  false;
        }

        if(!setAlertTimes(habit, habitID, db)) {
            return false
        }

        db.close()
        return true
    }

    fun getHabit(category: String): Habit {
        val db = this.readableDatabase

        return getHabit(category, db)!!
    }

    private fun setHabitInfo(habit: Habit, habitID : String, db: SQLiteDatabase) : Boolean {
        val cvHabits = contentValuesOf()

        cvHabits.put(ACTIVE_COL_HABIT_ID, habitID)
        cvHabits.put(ACTIVE_COL_CATEGORY, habit.category)
        cvHabits.put(ACTIVE_COL_FREQUENCY, habit.frequency)
        cvHabits.put(ACTIVE_COL_TIMES_PER_DAY, habit.timesPerDay)
        cvHabits.put(ACTIVE_COL_IS_ACTIVE, 1)
        val success = db.update(ACTIVE_HABITS_TABLE, cvHabits, ACTIVE_COL_HABIT_ID + " = ?", arrayOf(habitID))

        if(success == -1) {
            return false
        }
        return true
    }

    private fun setWeekdays(habit: Habit, habitID : String, db: SQLiteDatabase) : Boolean {
        val cvDaysOfWeek = contentValuesOf()
        cvDaysOfWeek.put(DAYS_COL_HABIT_ID, habitID)
        cvDaysOfWeek.put(DAYS_COL_ON_MONDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_THURSDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_WEDNESDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_THURSDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_FRIDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_SATURDAY, "0")
        cvDaysOfWeek.put(DAYS_COL_ON_SUNDAY, "0")

        for (weekday in habit.daysOfTheWeek!!) {
            cvDaysOfWeek.put(weekday, "1")
        }

        val success = db.update(DAYS_OF_THE_WEEK_TABLE, cvDaysOfWeek, DAYS_COL_HABIT_ID + " = ?", arrayOf(habitID))

        if(success == -1) {
            return false
        }
        return true
    }

    private fun setAlertTimes(habit: Habit, habitID: String, db: SQLiteDatabase) : Boolean{
        val cvTimeOfTheDay = contentValuesOf()
        if(habit.alertTimes != null) {
            for(alertTime in habit.alertTimes!!) {
                val hour = alertTime.substring(0, 2)
                val minutes = alertTime.substring(3, 5)
                cvTimeOfTheDay.put(TIME_COL_HABIT_ID, habitID)
                cvTimeOfTheDay.put(TIME_COL_HOUR, hour)
                cvTimeOfTheDay.put(TIME_COL_MINUTES, minutes)
                val success = db.insert(TIME_OF_THE_DAY_TABLE, null, cvTimeOfTheDay)

                if(success == -1.toLong()) {
                    return false
                }
            }
        }
        return true
    }

    fun deleteHabit(category: String) : Boolean {
        val db = this.writableDatabase

        val id = getHabitID(category, db)

        if(!resetHabitInfo(category, db)) {
            return false
        }

        if(!deleteAlarms(id, db)) {
            return false
        }

        if(!markHabitAsInactive(id, db)) {
            return false
        }

        db.close()
        return true
    }

    private fun resetHabitInfo(category: String, db: SQLiteDatabase) : Boolean {
        val id = getHabitID(category, db)
        val cv = contentValuesOf()

        cv.put(ACTIVE_COL_FREQUENCY, "daily")
        cv.put(ACTIVE_COL_TIMES_PER_DAY, 0)
        cv.put(ACTIVE_COL_IS_ACTIVE, 0)

        val success  = db.update(ACTIVE_HABITS_TABLE, cv, ACTIVE_COL_HABIT_ID +  " =?", arrayOf(id))

        if(success == -1) {
            return false
        }
        return true
    }

    private fun deleteAlarms(habitID: String, db: SQLiteDatabase) : Boolean {
        val success = db.delete(TIME_OF_THE_DAY_TABLE, TIME_COL_HABIT_ID + " =?", arrayOf(habitID))

        if(success == -1) {
            return false
        }
        return true
    }

    private fun markHabitAsInactive(habitID: String, db: SQLiteDatabase) : Boolean {
        val cvIsActive  = contentValuesOf()
        cvIsActive.put(ACTIVE_COL_IS_ACTIVE, "0")
        var success = db.update(ACTIVE_HABITS_TABLE, cvIsActive, TIME_COL_HABIT_ID + " =?", arrayOf(habitID))
        if(success == -1) {
            return false
        }
        val cvDaysOfTheWeek = contentValuesOf()
        cvDaysOfTheWeek.put(DAYS_COL_ON_MONDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_TUESDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_WEDNESDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_THURSDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_FRIDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_SATURDAY, "0")
        cvDaysOfTheWeek.put(DAYS_COL_ON_SUNDAY, "0")

        success = db.update(DAYS_OF_THE_WEEK_TABLE, cvDaysOfTheWeek, DAYS_COL_HABIT_ID + " =?", arrayOf(habitID))

        if(success == -1){
            return false
        }
        return true
    }



    fun isHabitActive(category: String) : Boolean {
        val db = this.readableDatabase
        val query = "SELECT " + ACTIVE_COL_IS_ACTIVE +  " FROM " + ACTIVE_HABITS_TABLE + " WHERE category = '$category'"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()) {
            val isActive = cursor.getString(0).toInt()
            if(isActive == 1) {
                return true;
            }
            return false
        }
        db.close()
        return false
    }

    fun todayHabits() : ArrayList<Habit> {
        val db = this.readableDatabase
        val list = ArrayList<Habit>()

        for(category in categories) {
            if(today(category, db)) {
                if(getHabit(category, db) != null) {
                    list.add(getHabit(category, db)!!)
                }
            }
        }

        return list
    }

    private fun today(category: String, db: SQLiteDatabase) : Boolean {
        val id = getHabitID(category, db)

        if(isHabitActive(category)) {
            val weekday = LocalDate.now().dayOfWeek.toString().lowercase()
            val query = "SELECT " + weekday +  " FROM " + DAYS_OF_THE_WEEK_TABLE + " WHERE " + DAYS_COL_HABIT_ID + " = '$id' "
            val cursor = db.rawQuery(query, null)
            if(cursor.moveToFirst()) {
                if(cursor.getString(0) == "1") {
                    return true
                } else {
                    return false
                }
            } else {
                return false
            }
        }
        return false
    }

    fun getHabit(category: String, db: SQLiteDatabase) : Habit? {
        var query = "SELECT * FROM " + ACTIVE_HABITS_TABLE + " WHERE " + ACTIVE_COL_CATEGORY + " = '$category'"
        var cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            val frequency = cursor.getString(2)
            val timesPerDay = cursor.getString(3).toInt()
            val isActive = cursor.getString(4).toInt()
            val daysOfTheWeek = getDaysOfTheWeek(category, db)
            val alertTimes = getAlertTimes(category, db)
            val completed = getCompleted(category, db)
            return Habit(category, frequency, timesPerDay, daysOfTheWeek, alertTimes, isActive, completed)
        }
        return null
    }

    private fun getDaysOfTheWeek(category: String, db: SQLiteDatabase) : ArrayList<String> {
        val habitID = getHabitID(category, db)
        val list = ArrayList<String>()
        val query = "SELECT * FROM " + DAYS_OF_THE_WEEK_TABLE + " WHERE " + DAYS_COL_HABIT_ID + " = '$habitID'"
        val cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                if(cursor.getString(1) == "1") {
                    list.add("monday")
                }

                if(cursor.getString(2) == "1") {
                    list.add("tuesday")
                }

                if(cursor.getString(3) == "1") {
                    list.add("wednesday")
                }

                if(cursor.getString(4) == "1") {
                    list.add("thursday")
                }

                if(cursor.getString(5) == "1") {
                    list.add("friday")
                }

                if(cursor.getString(6) == "1") {
                    list.add("saturday")
                }

                if(cursor.getString(7) == "1") {
                    list.add("sunday")
                }
            } while(cursor.moveToNext())
        }
        return list
    }

    private fun getAlertTimes(category: String, db:SQLiteDatabase) : ArrayList<String>?{
        val id = getHabitID(category, db)
        val list = ArrayList<String>()
        val query = "SELECT * FROM " + TIME_OF_THE_DAY_TABLE + " WHERE " + TIME_COL_HABIT_ID + " = '$id'"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()) {
            do {
                val hours = cursor.getString(2)
                val minutes = cursor.getString(3)
                list.add(hours + ":" + minutes)
            } while(cursor.moveToNext())
        }

        if(list.size == 0) {
            return null
        }
        return list
    }

    private fun getCompleted(category: String, db: SQLiteDatabase) : Int {
        val success = updateTodayInfo(db)

        if(!success) {
            return 0
        }

        val id = getHabitID(category, db)
        val query = "SELECT * FROM " + TODAY_INFO_TABLE + " WHERE " + TODAY_COL_HABIT_ID + " = '$id'"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()) {
            return cursor.getString(1).toInt()
        }

        return 0
    }

    fun updateCompleted(category: String, timesCompleted: Int) : Boolean{
        val db = this.writableDatabase
        val id = getHabitID(category, db)
        var success  = updateTodayInfo(db)
        if(!success) {
            return false
        }

        val query = "Select * " + " FROM " + TODAY_INFO_TABLE + " WHERE " + TODAY_COL_HABIT_ID +  " = " + "'$id'"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()) {
            val cv = contentValuesOf()
            cv.put(TODAY_COL_TIMES_COMPLETED, timesCompleted)
            val result = db.update(TODAY_INFO_TABLE, cv, TODAY_COL_HABIT_ID + " =?", arrayOf(cursor.getString(0)))

            if(result == -1) {
                return false
            }

            return true
        }

        return false
    }

    private fun updateTodayInfo(db: SQLiteDatabase) : Boolean{
        val query = "SELECT * FROM " + TODAY_INFO_TABLE
        var cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        if(cursor.moveToFirst()) {
            val storedDate = Date(cursor.getString(2))
            val today = Date()

            if(storedDate.day != today.day || storedDate.month != today.month || storedDate.year != today.year) {
                cursor = db.rawQuery(query, null)
                if(cursor.moveToFirst()) {
                    val cv = contentValuesOf()
                    cv.put(TODAY_COL_TIMES_COMPLETED, 0)
                    cv.put(TODAY_COL_DATE, Date().toString())
                    val success = db.update(TODAY_INFO_TABLE, cv, TODAY_COL_HABIT_ID + " =?", arrayOf(cursor.getString(0)))

                    if(success == -1) {
                        return false
                    }
                    return true
                }
            }
            return true
        }
        return false
    }

    fun printInfoFromAllTables() {
        val db = this.readableDatabase
        println("DATA FROM ALL TABLES")

        println("HABITS TABLE INFO")
        var query = "SELECT * FROM " + ACTIVE_HABITS_TABLE
        var cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                print("id = ")
                print(cursor.getString(0))
                print(" category = ")
                print(cursor.getString(1))
                print(" frequency = ")
                print(cursor.getString(2))
                print(" timesPerDay = ")
                print(cursor.getString(3))
                print(" isActive = ")
                println(cursor.getString(4))
            } while(cursor.moveToNext())
        }


        println("DAYS OF THE WEEK TABLE")
        query = "SELECT * FROM " + DAYS_OF_THE_WEEK_TABLE
        cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                print("habitID = ")
                print(cursor.getString(0))
                print(" monday = ")
                print(cursor.getString(1))
                print(" tuesday = ")
                print(cursor.getString(2))
                print(" wednesday = ")
                print(cursor.getString(3))
                print(" thursday = ")
                print(cursor.getString(4))
                print(" friday = ")
                print(cursor.getString(5))
                print(" saturday = ")
                print(cursor.getString(6))
                print(" sunday = ")
                println(cursor.getString(7))
            } while(cursor.moveToNext())
        }

        println("TIME OF THE DAY TABLE")
        query = "SELECT * FROM " + TIME_OF_THE_DAY_TABLE
        cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                print(" rowID = ")
                print(cursor.getString(0))
                print(" habitID = ")
                print(cursor.getString(1))
                print(" hour = ")
                print(cursor.getString(2))
                print(" minutes = ")
                println(cursor.getString(3))
            } while(cursor.moveToNext())
        }


        println("TODAY TABLE")
        query = "SELECT * FROM " + TODAY_INFO_TABLE
        cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                print("habitID = ")
                print(cursor.getString(0))
                print(" date = ")
                println(cursor.getString(1))
            } while(cursor.moveToNext())
        }


        println("RECORD TABLE")
        query = "SELECT * FROM " + RECORDS_TABLE
        cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                print("habitID = ")
                print(cursor.getString(0))
                print(" timesCompleted = ")
                print(cursor.getString(1))
                print(" date = ")
                println(cursor.getString(2))
            } while(cursor.moveToNext())
        }

        db.close()
        cursor.close()
    }
}