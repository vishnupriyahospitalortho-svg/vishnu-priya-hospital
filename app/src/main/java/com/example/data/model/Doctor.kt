package com.example.data.model

data class Doctor(
    val id: String,
    val name: String,
    val qualifications: String,
    val speciality: String,
    val department: String,
    val experienceYears: Int,
    val consultationFee: Int,
    val availableDays: String,
    val timing: String,
    val bio: String,
    val rating: Double = 5.0,
    val reviewCount: Int = 142
)

object DoctorRepository {
    val doctors = listOf(
        Doctor(
            id = "dr_naresh",
            name = "Dr. Naresh",
            qualifications = "MBBS, DNB / MS (Orthopaedics)",
            speciality = "Chief Orthopedic & Joint Replacement Surgeon",
            department = "Orthopaedics & Joint Surgery",
            experienceYears = 17,
            consultationFee = 400,
            availableDays = "Mon - Sat",
            timing = "10:00 AM - 02:00 PM & 05:00 PM - 09:00 PM",
            bio = "Highly acclaimed Senior Orthopedic Surgeon with 17+ years of expertise in Total Knee & Hip Replacement, Complex Fracture fixation, Spine disc disorders, and Arthroscopic ligament reconstruction.",
            rating = 5.0,
            reviewCount = 280
        ),
        Doctor(
            id = "dr_archana",
            name = "Dr. Archana Odnala",
            qualifications = "BDS",
            speciality = "Consultant Dental Surgeon",
            department = "Dental Surgery & Oral Healthcare",
            experienceYears = 12,
            consultationFee = 300,
            availableDays = "Mon - Sat",
            timing = "10:00 AM - 02:00 PM & 05:00 PM - 08:30 PM",
            bio = "Experienced Dental Surgeon providing comprehensive dental care, root canal treatments, painless tooth extractions, crowns & bridges, pediatric dentistry, and cosmetic smile designing.",
            rating = 4.9,
            reviewCount = 165
        )
    )
}
