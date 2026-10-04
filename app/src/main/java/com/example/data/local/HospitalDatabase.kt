package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.Appointment
import com.example.data.model.LocationReview

@Database(entities = [Appointment::class, LocationReview::class], version = 2, exportSchema = false)
abstract class HospitalDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
    abstract fun locationReviewDao(): LocationReviewDao

    companion object {
        @Volatile
        private var INSTANCE: HospitalDatabase? = null

        fun getDatabase(context: Context): HospitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HospitalDatabase::class.java,
                    "vishnu_priya_hospital_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
