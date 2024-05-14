package com.kalinasoft.stretchtimer

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allExercise: Flow<List<Exercise>> = exerciseDAO.getExercises()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(exercise: Exercise) {
        exerciseDAO.insert(exercise)
    }
}