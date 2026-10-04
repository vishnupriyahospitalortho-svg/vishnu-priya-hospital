package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppointmentRepository
import com.example.data.local.HospitalDatabase
import com.example.data.local.LocationReviewRepository
import com.example.data.model.DoctorRepository
import com.example.data.model.HospitalInfo
import com.example.data.model.LocationRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    private lateinit var database: HospitalDatabase
    private lateinit var repository: AppointmentRepository
    private lateinit var reviewRepository: LocationReviewRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, HospitalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = AppointmentRepository(database.appointmentDao())
        reviewRepository = LocationReviewRepository(database.locationReviewDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun read_string_from_context() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Vishnu Priya Hospital", appName)
    }

    @Test
    fun hospital_details_and_doctors_are_configured() {
        assertEquals("Vishnu Priya Hospital", HospitalInfo.NAME)
        assertEquals("https://maps.app.goo.gl/fBNnkB2yJRsYbxzb7", HospitalInfo.GOOGLE_MAPS_URL)
        assertTrue(DoctorRepository.doctors.isNotEmpty())
        val drNaresh = DoctorRepository.doctors.firstOrNull { it.id == "dr_naresh" }
        assertNotNull(drNaresh)
        assertEquals("Dr. Naresh", drNaresh?.name)
        val drArchana = DoctorRepository.doctors.firstOrNull { it.id == "dr_archana" }
        assertNotNull(drArchana)
        assertEquals("Dr. Archana Odnala", drArchana?.name)
        assertEquals("BDS", drArchana?.qualifications)
    }

    @Test
    fun single_hospital_building_and_address_configured() {
        val hospital = LocationRepository.HOSPITAL_MAIN
        assertNotNull(hospital)
        assertEquals("Vishnu Priya Hospital", hospital.name)
        assertEquals(HospitalInfo.ADDRESS, hospital.address)
        assertEquals("Opposite Old Bus Stand, NH63 Highway, Korutla", hospital.landmark)
        assertTrue(hospital.casualtyAvailable)
        assertEquals(1, LocationRepository.locations.size) // only one single hospital building
    }

    @Test
    fun room_database_insert_and_retrieve_location_review() = runBlocking {
        val insertedId = reviewRepository.addReview(
            locationId = "vishnu_priya_hospital",
            reviewerName = "K. Rajesh",
            rating = 5,
            visitReason = "Total Knee Replacement",
            comment = "Excellent treatment and fast recovery for my father."
        )

        assertTrue(insertedId > 0)

        val reviews = reviewRepository.getReviewsForLocation("vishnu_priya_hospital").first()
        assertEquals(1, reviews.size)
        assertEquals("K. Rajesh", reviews.first().reviewerName)
        assertEquals(5, reviews.first().rating)
        assertEquals("Total Knee Replacement", reviews.first().visitReason)
    }

    @Test
    fun initial_location_reviews_seed_correctly() = runBlocking {
        reviewRepository.populateInitialReviewsIfEmpty()
        val all = reviewRepository.allReviews.first()
        assertTrue(all.isNotEmpty())
        assertTrue(all.any { it.reviewerName == "Srinivas Rao" })
        assertTrue(all.any { it.reviewerName == "Pooja Varma" })
    }

    @Test
    fun room_database_insert_and_retrieve_appointment() = runBlocking {
        val created = repository.createAppointment(
            patientName = "Ravi Kumar",
            patientAge = 35,
            patientGender = "Male",
            patientPhone = "9515574014",
            doctorName = "Dr. Naresh",
            department = "Orthopaedics & Joint Surgery",
            appointmentDate = "Today",
            timeSlot = "10:30 AM",
            symptoms = "Right knee strain after sports"
        )

        assertNotNull(created)
        assertTrue(created.tokenNumber.startsWith("VP-"))
        assertEquals("Confirmed", created.status)

        val list = repository.allAppointments.first()
        assertEquals(1, list.size)
        assertEquals("Ravi Kumar", list.first().patientName)
    }
}
