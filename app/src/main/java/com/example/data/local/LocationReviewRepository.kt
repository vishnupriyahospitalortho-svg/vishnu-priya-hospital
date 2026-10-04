package com.example.data.local

import com.example.data.model.LocationReview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocationReviewRepository(private val reviewDao: LocationReviewDao) {

    val allReviews: Flow<List<LocationReview>> = reviewDao.getAllReviews()

    fun getReviewsForLocation(locationId: String = "vishnu_priya_hospital"): Flow<List<LocationReview>> {
        return reviewDao.getAllReviews()
    }

    fun getAverageRating(locationId: String = "vishnu_priya_hospital"): Flow<Double?> {
        return reviewDao.getAverageRatingForLocation(locationId)
    }

    suspend fun addReview(
        locationId: String = "vishnu_priya_hospital",
        reviewerName: String,
        rating: Int,
        visitReason: String,
        comment: String
    ): Long = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        val formattedDate = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(now))
        val review = LocationReview(
            locationId = "vishnu_priya_hospital",
            reviewerName = reviewerName.trim(),
            rating = rating.coerceIn(1, 5),
            visitReason = visitReason.ifBlank { "General Consultation" },
            comment = comment.trim(),
            formattedDate = formattedDate,
            timestamp = now
        )
        reviewDao.insertReview(review)
    }

    suspend fun populateInitialReviewsIfEmpty() = withContext(Dispatchers.IO) {
        if (reviewDao.getTotalReviewCount() == 0) {
            val initialList = listOf(
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Srinivas Rao",
                    rating = 5,
                    visitReason = "Total Knee Replacement",
                    comment = "Dr. Naresh sir did knee replacement surgery for my mother. She walked with support on day 2 itself and now walks completely pain-free! Clean hospital, supportive nursing staff, and excellent post-operative care.",
                    formattedDate = "2 days ago",
                    timestamp = System.currentTimeMillis() - 2 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Mohammad Afzal",
                    rating = 5,
                    visitReason = "Emergency Trauma & Fracture",
                    comment = "Visited at midnight 1:30 AM after my brother had a road bike accident. 24/7 casualty responded within seconds. Digital X-ray taken immediately and Dr. Naresh performed emergency reduction. Lifesaver hospital in Korutla!",
                    formattedDate = "5 days ago",
                    timestamp = System.currentTimeMillis() - 5 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Rajeshwar Goud",
                    rating = 5,
                    visitReason = "Slip Disc & Sciatica",
                    comment = "Experienced doctors and very honest advice. They don't push unnecessary operations. Proper medication and guidance helped me recover from severe sciatica pain. Nominal direct consultation fees.",
                    formattedDate = "1 week ago",
                    timestamp = System.currentTimeMillis() - 7 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Pooja Varma",
                    rating = 5,
                    visitReason = "Painless Root Canal (RCT)",
                    comment = "Dr. Archana Odnala madam is truly wonderful and gentle! I was terrified of root canal treatment, but she made the whole procedure totally painless. Clean clinic and modern dental chair inside the hospital.",
                    formattedDate = "Yesterday",
                    timestamp = System.currentTimeMillis() - 1 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Kavitha Reddy",
                    rating = 5,
                    visitReason = "Post-Surgery Physiotherapy",
                    comment = "The IFT therapy and motorized traction machine here are top class. The physiotherapist guided me step-by-step through mobility exercises after my hip surgery. Regained full walking confidence!",
                    formattedDate = "3 days ago",
                    timestamp = System.currentTimeMillis() - 3 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Ch. Sai Kumar",
                    rating = 5,
                    visitReason = "Dental Wisdom Tooth Extraction",
                    comment = "Had painful impacted wisdom tooth. Dr. Archana madam completed surgical removal in just 20 minutes with zero discomfort. Quick recovery and transparent billing at hospital reception desk.",
                    formattedDate = "4 days ago",
                    timestamp = System.currentTimeMillis() - 4 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "B. Vittal",
                    rating = 5,
                    visitReason = "Cervical Spondylosis",
                    comment = "Suffered from neck stiffness and shoulder pain for months due to long driving. 10 sessions of ultrasonic therapy and neck traction completely resolved my numbness. Everything under one roof at Old Bus Stand!",
                    formattedDate = "6 days ago",
                    timestamp = System.currentTimeMillis() - 6 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "Lakshmi Narayana",
                    rating = 4,
                    visitReason = "General Ortho OPD",
                    comment = "Very good treatment and fast token system in morning OPD. Waiting area is clean and pharmacy is available 24 hours right downstairs in the same building.",
                    formattedDate = "2 weeks ago",
                    timestamp = System.currentTimeMillis() - 14 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "K. Thirupathi",
                    rating = 5,
                    visitReason = "Digital X-Ray & Blood Tests",
                    comment = "Superfast digital X-Ray imaging right at the hospital. Reports were generated within 20 minutes for Dr. Naresh to review. 24/7 pharmacy downstairs had all prescribed medicines in stock.",
                    formattedDate = "3 days ago",
                    timestamp = System.currentTimeMillis() - 3 * 86400000L
                ),
                LocationReview(
                    locationId = "vishnu_priya_hospital",
                    reviewerName = "G. Ravi Kumar",
                    rating = 5,
                    visitReason = "Total Knee Replacement Stay",
                    comment = "Underwent knee replacement surgery in the modular OT. Exceptionally clean and hygienic rooms. The nursing staff attended promptly around the clock. Great care at the Old Bus Stand hospital.",
                    formattedDate = "4 days ago",
                    timestamp = System.currentTimeMillis() - 4 * 86400000L
                )
            )
            reviewDao.insertReviews(initialList)
        }
    }
}
