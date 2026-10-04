package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.LocationReview
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationReviewDao {

    @Query("SELECT * FROM location_reviews WHERE locationId = :locationId ORDER BY timestamp DESC")
    fun getReviewsForLocation(locationId: String): Flow<List<LocationReview>>

    @Query("SELECT * FROM location_reviews ORDER BY timestamp DESC")
    fun getAllReviews(): Flow<List<LocationReview>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: LocationReview): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<LocationReview>)

    @Query("DELETE FROM location_reviews WHERE id = :id")
    suspend fun deleteReview(id: Long)

    @Query("SELECT COUNT(*) FROM location_reviews WHERE locationId = :locationId")
    suspend fun getReviewCountForLocation(locationId: String): Int

    @Query("SELECT COUNT(*) FROM location_reviews")
    suspend fun getTotalReviewCount(): Int

    @Query("SELECT AVG(rating) FROM location_reviews WHERE locationId = :locationId")
    fun getAverageRatingForLocation(locationId: String): Flow<Double?>
}
