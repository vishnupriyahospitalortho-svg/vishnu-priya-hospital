package com.example.data.model

data class HospitalLocation(
    val id: String = "vishnu_priya_hospital",
    val name: String = HospitalInfo.NAME,
    val shortName: String = "Vishnu Priya Hospital (Korutla)",
    val category: String = "Ortho, Trauma Emergency & Multi-Speciality Hospital",
    val address: String = HospitalInfo.ADDRESS,
    val landmark: String = "Opposite Old Bus Stand, NH63 Highway, Korutla",
    val phone: String = HospitalInfo.PHONE_PRIMARY,
    val phoneDisplay: String = HospitalInfo.PHONE_PRIMARY_DISPLAY,
    val timings: String = "Casualty: 24/7 Open | OPD: 10:00 AM - 2:00 PM & 5:00 PM - 9:00 PM",
    val casualtyAvailable: Boolean = true,
    val leadDoctor: String = "Dr. Naresh (Chief Orthopedic Surgeon) & Dr. Archana Odnala (Dental Surgeon)",
    val description: String = "Vishnu Priya Hospital operates entirely within a single comprehensive medical building at Old Bus Stand, Korutla. All clinical facilities—including the 24/7 Trauma Emergency, Modular Operation Theatre, Inpatient Recovery Wards, Dr. Naresh's Orthopedic & Joint Replacement OPD, Dr. Archana Odnala's Dental Surgery Clinic, Physiotherapy Rehabilitation Wing, Digital X-Ray, Automated Pathology Lab, and 24/7 Pharmacy—are located together under one roof.",
    val services: List<String> = listOf(
        "24/7 Emergency Trauma & Fracture Care",
        "Total Knee & Hip Replacement (TKR & THR)",
        "Arthroscopic Ligament Reconstruction (ACL/PCL)",
        "Spine & Sciatica Nerve Decompression",
        "Painless Root Canal & Dental Surgery (Dr. Archana)",
        "Physiotherapy & Spinal Traction Rehab Wing",
        "Low-Radiation Digital High-Frequency X-Ray",
        "Fully Automated In-House Pathology Lab",
        "24/7 In-Hospital Dispensing Pharmacy",
        "Ultra-Clean Modular Operation Theatre (OT)",
        "A/C & General Inpatient Care Wards"
    ),
    val googleMapsUrl: String = HospitalInfo.GOOGLE_MAPS_URL,
    val initialRating: Double = 5.0,
    val initialReviewCount: Int = 450
)

object LocationRepository {
    val HOSPITAL_MAIN = HospitalLocation()

    val locations = listOf(HOSPITAL_MAIN)

    fun getLocationById(id: String? = null): HospitalLocation {
        return HOSPITAL_MAIN
    }
}
