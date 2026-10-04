package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppointmentRepository
import com.example.data.local.HospitalDatabase
import com.example.data.local.LocationReviewRepository
import com.example.data.model.Appointment
import com.example.data.model.DepartmentInfo
import com.example.data.model.Doctor
import com.example.data.model.DoctorRepository
import com.example.data.model.HospitalInfo
import com.example.data.model.HospitalLocation
import com.example.data.model.LocationRepository
import com.example.data.model.LocationReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

enum class AppScreen {
    HOME,
    OPD_DOCTORS,
    DEPARTMENTS,
    LOCATIONS,
    PAYMENT_POLICY,
    GUIDE
}

data class BookingFormState(
    val patientName: String = "",
    val patientAge: String = "",
    val patientGender: String = "Male",
    val patientPhone: String = "",
    val selectedDoctor: Doctor = DoctorRepository.doctors.first(),
    val selectedDate: String = "",
    val selectedTimeSlot: String = "10:30 AM",
    val symptoms: String = "",
    val errorMessage: String? = null,
    val isSubmitting: Boolean = false,
    val lastBookedAppointment: Appointment? = null
)

class HospitalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppointmentRepository
    private val locationReviewRepository: LocationReviewRepository

    val appointments: StateFlow<List<Appointment>>
    val allLocationReviews: StateFlow<List<LocationReview>>

    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _bookingState = MutableStateFlow(BookingFormState())
    val bookingState: StateFlow<BookingFormState> = _bookingState.asStateFlow()

    private val _selectedDepartment = MutableStateFlow<DepartmentInfo?>(null)
    val selectedDepartment: StateFlow<DepartmentInfo?> = _selectedDepartment.asStateFlow()

    private val _selectedDoctorDetail = MutableStateFlow<Doctor?>(null)
    val selectedDoctorDetail: StateFlow<Doctor?> = _selectedDoctorDetail.asStateFlow()

    private val _selectedLocation = MutableStateFlow<HospitalLocation?>(null)
    val selectedLocation: StateFlow<HospitalLocation?> = _selectedLocation.asStateFlow()

    private val _showEmergencyDialog = MutableStateFlow(false)
    val showEmergencyDialog: StateFlow<Boolean> = _showEmergencyDialog.asStateFlow()

    private val _showTokenSuccessDialog = MutableStateFlow(false)
    val showTokenSuccessDialog: StateFlow<Boolean> = _showTokenSuccessDialog.asStateFlow()

    init {
        val db = HospitalDatabase.getDatabase(application)
        repository = AppointmentRepository(db.appointmentDao())
        locationReviewRepository = LocationReviewRepository(db.locationReviewDao())

        appointments = repository.allAppointments.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        allLocationReviews = locationReviewRepository.allReviews.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        val defaultDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(Date())
        _bookingState.value = _bookingState.value.copy(selectedDate = defaultDate)

        viewModelScope.launch {
            repository.populateSampleIfEmpty()
            locationReviewRepository.populateInitialReviewsIfEmpty()
        }
    }

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun openDoctorDetail(doctor: Doctor) {
        _selectedDoctorDetail.value = doctor
    }

    fun closeDoctorDetail() {
        _selectedDoctorDetail.value = null
    }

    fun openDepartmentDetail(department: DepartmentInfo) {
        _selectedDepartment.value = department
    }

    fun closeDepartmentDetail() {
        _selectedDepartment.value = null
    }

    fun openLocationDetails(location: HospitalLocation) {
        _selectedLocation.value = location
    }

    fun openLocationDetailsById(locationId: String) {
        val loc = LocationRepository.getLocationById(locationId)
        if (loc != null) {
            _selectedLocation.value = loc
        }
    }

    fun closeLocationDetails() {
        _selectedLocation.value = null
    }

    fun getReviewsForLocation(locationId: String): Flow<List<LocationReview>> {
        return locationReviewRepository.getReviewsForLocation(locationId)
    }

    fun submitLocationReview(
        locationId: String,
        reviewerName: String,
        rating: Int,
        visitReason: String,
        comment: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        if (reviewerName.isBlank()) {
            onComplete(false, "Please enter your name")
            return
        }
        if (rating < 1 || rating > 5) {
            onComplete(false, "Please select a star rating (1-5)")
            return
        }
        if (comment.isBlank()) {
            onComplete(false, "Please write your review feedback")
            return
        }

        viewModelScope.launch {
            try {
                locationReviewRepository.addReview(
                    locationId = locationId,
                    reviewerName = reviewerName,
                    rating = rating,
                    visitReason = visitReason,
                    comment = comment
                )
                onComplete(true, null)
            } catch (e: Exception) {
                onComplete(false, e.localizedMessage ?: "Failed to save review")
            }
        }
    }

    fun setEmergencyDialogVisible(visible: Boolean) {
        _showEmergencyDialog.value = visible
    }

    fun setTokenSuccessDialogVisible(visible: Boolean) {
        _showTokenSuccessDialog.value = visible
    }

    // Booking form updates
    fun updatePatientName(name: String) {
        _bookingState.value = _bookingState.value.copy(patientName = name, errorMessage = null)
    }

    fun updatePatientAge(age: String) {
        if (age.isEmpty() || age.all { it.isDigit() }) {
            _bookingState.value = _bookingState.value.copy(patientAge = age, errorMessage = null)
        }
    }

    fun updatePatientGender(gender: String) {
        _bookingState.value = _bookingState.value.copy(patientGender = gender)
    }

    fun updatePatientPhone(phone: String) {
        if (phone.length <= 10 && phone.all { it.isDigit() }) {
            _bookingState.value = _bookingState.value.copy(patientPhone = phone, errorMessage = null)
        }
    }

    fun selectDoctor(doctor: Doctor) {
        _bookingState.value = _bookingState.value.copy(selectedDoctor = doctor)
    }

    fun selectDate(date: String) {
        _bookingState.value = _bookingState.value.copy(selectedDate = date)
    }

    fun selectTimeSlot(time: String) {
        _bookingState.value = _bookingState.value.copy(selectedTimeSlot = time)
    }

    fun updateSymptoms(symptoms: String) {
        _bookingState.value = _bookingState.value.copy(symptoms = symptoms)
    }

    fun selectDoctorForOpd(doctor: Doctor) {
        _selectedDoctorDetail.value = null
        _currentScreen.value = AppScreen.OPD_DOCTORS
    }

    fun bookAppointment() {
        val state = _bookingState.value
        if (state.patientName.isBlank()) {
            _bookingState.value = state.copy(errorMessage = "Please enter patient full name")
            return
        }
        val age = state.patientAge.toIntOrNull()
        if (age == null || age <= 0 || age > 120) {
            _bookingState.value = state.copy(errorMessage = "Please enter a valid patient age")
            return
        }
        if (state.patientPhone.length < 10) {
            _bookingState.value = state.copy(errorMessage = "Please enter a valid 10-digit mobile number")
            return
        }

        viewModelScope.launch {
            _bookingState.value = _bookingState.value.copy(isSubmitting = true)
            try {
                val created = repository.createAppointment(
                    patientName = state.patientName,
                    patientAge = age,
                    patientGender = state.patientGender,
                    patientPhone = state.patientPhone,
                    doctorName = state.selectedDoctor.name,
                    department = state.selectedDoctor.department,
                    appointmentDate = state.selectedDate.ifBlank { "Today" },
                    timeSlot = state.selectedTimeSlot,
                    symptoms = state.symptoms.ifBlank { "General Orthopaedic Consultation" }
                )
                _bookingState.value = _bookingState.value.copy(
                    isSubmitting = false,
                    lastBookedAppointment = created,
                    errorMessage = null
                )
                _showTokenSuccessDialog.value = true
            } catch (e: Exception) {
                _bookingState.value = _bookingState.value.copy(
                    isSubmitting = false,
                    errorMessage = "Error booking appointment: ${e.message}"
                )
            }
        }
    }

    fun resetBookingForm() {
        val defaultDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(Date())
        _bookingState.value = BookingFormState(selectedDate = defaultDate)
    }

    fun cancelAppointment(id: Long) {
        viewModelScope.launch {
            repository.cancelAppointment(id)
        }
    }

    fun deleteAppointment(id: Long) {
        viewModelScope.launch {
            repository.deleteAppointment(id)
        }
    }
}
