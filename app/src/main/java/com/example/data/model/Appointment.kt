package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val tokenNumber: String,
    val patientName: String,
    val patientAge: Int,
    val patientGender: String,
    val patientPhone: String,
    val doctorName: String,
    val department: String,
    val appointmentDate: String,
    val timeSlot: String,
    val symptoms: String,
    val status: String = "Confirmed", // "Confirmed", "Completed", "Cancelled"
    val createdAt: Long = System.currentTimeMillis()
)
