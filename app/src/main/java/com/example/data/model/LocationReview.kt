package com.example.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "location_reviews",
    indices = [Index(value = ["locationId"])]
)
data class LocationReview(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val locationId: String,
    val reviewerName: String,
    val rating: Int, // 1 to 5 stars
    val visitReason: String,
    val comment: String,
    val formattedDate: String,
    val timestamp: Long = System.currentTimeMillis()
)
