package com.example.data.model

data class DepartmentInfo(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyTreatments: List<String>,
    val doctorName: String
)

data class PatientReview(
    val id: String,
    val patientName: String,
    val town: String,
    val rating: Int,
    val review: String,
    val treatmentType: String,
    val date: String
)

object HospitalInfo {
    const val NAME = "Vishnu Priya Hospital"
    const val TAGLINE = "Center for Orthopaedics, Joint Replacement & 24/7 Trauma Care"
    const val ADDRESS = "Old Bus Stand, Mancherial - Nizamabad Road, NH63, Korutla, Jagtial Dist, Telangana - 505326"
    const val PHONE_PRIMARY = "+919515574014"
    const val PHONE_PRIMARY_DISPLAY = "+91 95155 74014"
    const val PHONE_SECONDARY = "09885755957"
    const val PHONE_SECONDARY_DISPLAY = "098857 55957"
    const val EMAIL = "vishnupriyahospitalortho@gmail.com"
    const val GOOGLE_MAPS_URL = "https://maps.app.goo.gl/fBNnkB2yJRsYbxzb7"
    const val RATING = 5.0
    const val TOTAL_REVIEWS = 450
    const val CASUALTY_HOURS = "Open 24 Hours / 7 Days"
    const val OPD_TIMINGS = "Morning: 10:00 AM – 2:00 PM | Evening: 5:00 PM – 9:00 PM"
    const val PAYMENT_POLICY_TITLE = "Direct Payment Only"
    const val PAYMENT_POLICY_SUMMARY = "No insurance claims, cashless TPA, or Mediclaim policies processed. All consultations, diagnostics, and treatments are strictly direct payment via Cash, UPI (GPay/PhonePe), or Cards at the hospital billing desk."
    const val WALK_IN_POLICY_TITLE = "Walk-in OPD Consultations"
    const val WALK_IN_POLICY_SUMMARY = "No online bookings. Walk-in tokens are issued directly at the hospital reception desk on a first-come, first-served basis starting at 9:30 AM."

    val departments = listOf(
        DepartmentInfo(
            id = "ortho_joint",
            title = "Orthopaedics & Joint Replacement",
            subtitle = "Knee, Hip & Shoulder Arthroplasty",
            description = "Advanced computer-guided joint replacement, arthroscopic ligament surgery, and minimally invasive joint restoration for active living without pain.",
            keyTreatments = listOf(
                "Total Knee Replacement (TKR)",
                "Total Hip Replacement (THR)",
                "Partial Knee Arthroplasty",
                "Revision Joint Surgery",
                "Geriatric Bone Health"
            ),
            doctorName = "Dr. Naresh, MBBS, DNB / MS Ortho"
        ),
        DepartmentInfo(
            id = "trauma_fracture",
            title = "24/7 Trauma & Fracture Care",
            subtitle = "Immediate Accident & Emergency Surgery",
            description = "Dedicated polytrauma unit equipped for complex compound fractures, pelvic injuries, tendon repairs, and high-energy accident resuscitation.",
            keyTreatments = listOf(
                "24x7 Emergency Fracture Reduction",
                "Intramedullary Nailing & Plating",
                "Pediatric Fracture Care",
                "Tendon & Ligament Repair",
                "Dislocation Management"
            ),
            doctorName = "Dr. Naresh & 24/7 Trauma Team"
        ),
        DepartmentInfo(
            id = "spine_care",
            title = "Spine & Back Pain Center",
            subtitle = "Sciatica, Slip Disc & Spondylosis",
            description = "Comprehensive medical and surgical management for spine conditions, herniated discs, spinal cord decompression, and persistent lumbar pain.",
            keyTreatments = listOf(
                "Slip Disc & Sciatica Management",
                "Cervical Spondylosis Relief",
                "Spinal Injections & Block Therapy",
                "Lumbar Canal Stenosis Care",
                "Postural Correction Protocols"
            ),
            doctorName = "Dr. Naresh"
        ),
        DepartmentInfo(
            id = "arthroscopy_sports",
            title = "Arthroscopy & Sports Medicine",
            subtitle = "Keyhole Surgery & Ligament Care",
            description = "Minimally invasive arthroscopic surgery for sports athletes, knee ACL/PCL tears, meniscus preservation, and recurrent shoulder dislocations.",
            keyTreatments = listOf(
                "ACL & PCL Ligament Reconstruction",
                "Meniscus Repair & Balancing",
                "Shoulder Rotator Cuff Surgery",
                "Ankle Sprain & Cartilage Care",
                "Sports Injury Fast-Track Rehab"
            ),
            doctorName = "Dr. Naresh"
        ),
        DepartmentInfo(
            id = "physiotherapy",
            title = "Physiotherapy & Rehabilitation",
            subtitle = "State-of-the-art Recovery Unit",
            description = "Targeted recovery programs including electrotherapy, ultrasonic therapy, spinal traction, and manual mobility exercises for post-op and chronic ailments.",
            keyTreatments = listOf(
                "Post-Surgical Joint Mobilization",
                "IFT, TENS & Ultrasound Therapy",
                "Cervical & Lumbar Traction",
                "Paralysis & Stroke Rehabilitation",
                "Muscle Strengthening & Gait Training"
            ),
            doctorName = "Physiotherapy & Rehabilitation Unit"
        ),
        DepartmentInfo(
            id = "dental_surgery",
            title = "Dental Surgery & Oral Healthcare",
            subtitle = "Root Canal, Extractions & Smile Design",
            description = "Comprehensive modern dental care providing painless root canal therapy, teeth replacement, cosmetic dentistry, crowns & bridges, pediatric dentistry, and preventive oral health.",
            keyTreatments = listOf(
                "Painless Root Canal Treatment (RCT)",
                "Tooth Extraction & Impactions",
                "Ceramic Crowns & Fixed Bridges",
                "Teeth Scaling & Deep Cleaning",
                "Pediatric Dental Care & Fillings"
            ),
            doctorName = "Dr. Archana Odnala, BDS (Dental Surgeon)"
        ),
        DepartmentInfo(
            id = "diagnostics_pharmacy",
            title = "Digital X-Ray, Lab & Pharmacy",
            subtitle = "Quick In-House Diagnostic Support",
            description = "Ultra-low radiation high-resolution digital X-Ray imaging, fully automated pathology tests, and 24/7 in-hospital dispensing pharmacy.",
            keyTreatments = listOf(
                "Digital High-Frequency X-Ray",
                "Complete Blood & Biochemical Profiles",
                "Bone Mineral Density Assessment",
                "24/7 Emergency Pharmacy",
                "Pre-Operative Fitness Workup"
            ),
            doctorName = "Diagnostics & Pharmacy Team"
        )
    )

    val patientReviews = listOf(
        PatientReview(
            id = "r1",
            patientName = "Srinivas Rao",
            town = "Korutla",
            rating = 5,
            review = "Dr. Naresh sir is the best orthopedic surgeon in our region! My mother underwent knee replacement surgery here. She is walking comfortably without any pain. The staff's care and clean hospital environment gave us great confidence.",
            treatmentType = "Total Knee Replacement",
            date = "2 weeks ago"
        ),
        PatientReview(
            id = "r2",
            patientName = "Mohammad Afzal",
            town = "Metpally",
            rating = 5,
            review = "Visited at midnight for a bike accident fracture emergency. The doctors attended immediately, performed X-Ray, and stabilized the fracture with wonderful precision. Highly grateful to Vishnu Priya Hospital team!",
            treatmentType = "Trauma & Fracture Care",
            date = "1 month ago"
        ),
        PatientReview(
            id = "r3",
            patientName = "Kavitha Reddy",
            town = "Jagtial",
            rating = 5,
            review = "Suffered from chronic severe lower back pain for 6 months. Dr. Naresh gave the right diagnosis and treatment plan without unnecessary surgeries. The physiotherapy sessions here worked wonders for my mobility.",
            treatmentType = "Spine & Sciatica Therapy",
            date = "1 month ago"
        ),
        PatientReview(
            id = "r4",
            patientName = "Rajeshwar Goud",
            town = "Korutla Rural",
            rating = 5,
            review = "Very cooperative and humane doctors. Token system and OPD consultation are fast. Nominal charges and genuine medical advice. Truly a blessing for Korutla and surrounding villages.",
            treatmentType = "General Ortho OPD",
            date = "2 months ago"
        )
    )

    val facilities = listOf(
        "24/7 Casualty & Trauma Unit",
        "Modern Modular Operation Theatre (OT)",
        "Digital High-Frequency X-Ray",
        "Dedicated Physiotherapy Center",
        "A/C & Non-A/C Inpatient Rooms",
        "24/7 In-House Pharmacy",
        "Emergency Ambulance Facility",
        "Direct Billing (Cash, UPI & Card Accepted)"
    )
}
