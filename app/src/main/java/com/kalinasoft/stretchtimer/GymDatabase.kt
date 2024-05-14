package com.kalinasoft.stretchtimer

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
public abstract class GymDatabase:RoomDatabase() {
    abstract fun exerciseDAO(): ExerciseDAO
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "exercise_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}