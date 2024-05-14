package com.kalinasoft.stretchtimer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val duration:Float,
    val reps:Int,
    val sets:Int,
    val restSets: Int
)
