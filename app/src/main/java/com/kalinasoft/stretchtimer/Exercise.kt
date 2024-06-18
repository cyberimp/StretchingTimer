package com.kalinasoft.stretchtimer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val duration: Float,
    val reps: Int,
    val sets: Int,
    val restSets: Int
): Serializable
