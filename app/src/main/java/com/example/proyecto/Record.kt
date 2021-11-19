package com.example.proyecto

import java.util.*

class Record {
    var category : String = ""
    var completedDate : Date = Date()

    constructor(category: String, completedDate: Date) {
        this.category = category
        this.completedDate = completedDate
    }
}