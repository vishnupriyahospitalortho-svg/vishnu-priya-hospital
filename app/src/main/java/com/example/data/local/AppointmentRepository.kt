package com.example.data.local

import com.example.data.model.Appointment
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class AppointmentRepository(private val appointmentDao: AppointmentDao) {

    val allAppointments: Flow<List<Appointment>> = appointmentDao.getAllAppointments()
    val confirmedAppointments: Flow<List<Appointment>> = appointmentDao.getConfirmedAppointments()

    suspend fun createAppointment(
        patientName: String,
        patientAge: Int,
        patientGender: String,
        patientPhone: String,
        doctorName: String,
        department: String,
        appointmentDate: String,
        timeSlot: String,
        symptoms: String
    ): Appointment {
        val randomSuffix = Random.nextInt(100, 999)
        val tokenNumber = "VP-${SimpleDateFormat("dd", Locale.getDefault()).format(Date())}$randomSuffix"

        val appointment = Appointment(
            tokenNumber = tokenNumber,
            patientName = patientName.trim(),
            patientAge = patientAge,
            patientGender = patientGender,
            patientPhone = patientPhone.trim(),
            doctorName = doctorName,
            department = department,
            appointmentDate = appointmentDate,
            timeSlot = timeSlot,
            symptoms = symptoms.trim(),
            status = "Confirmed"
        )
        val newId = appointmentDao.insertAppointment(appointment)
        return appointment.copy(id = newId)
    }

    suspend fun cancelAppointment(id: Long) {
        appointmentDao.updateStatus(id, "Cancelled")
    }

    suspend fun deleteAppointment(id: Long) {
        appointmentDao.deleteAppointmentById(id)
    }

    suspend fun populateSampleIfEmpty() {
        if (appointmentDao.getAppointmentCount() == 0) {
            val sample = Appointment(
                tokenNumber = "VP-0142",
                patientName = "Venkatesh K.",
                patientAge = 46,
                patientGender = "Male",
                patientPhone = "9876543210",
                doctorName = "Dr. Naresh",
                department = "Orthopaedics & Joint Surgery",
                appointmentDate = "Today",
                timeSlot = "11:30 AM",
                symptoms = "Left knee pain while climbing stairs, morning stiffness.",
                status = "Confirmed"
            )
            appointmentDao.insertAppointment(sample)
        }
    }
}
