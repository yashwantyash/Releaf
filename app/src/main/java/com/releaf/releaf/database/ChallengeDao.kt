package com.releaf.releaf.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.releaf.releaf.models.Challenge
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {
    @Upsert
    suspend fun upsertChallenge(challenge: Challenge)

    @Delete
    suspend fun deleteChallenge(challenge: Challenge)

    @Insert
    suspend fun insertChallenge(challenge: Challenge): Long

    @Query("SELECT * FROM challenge ORDER BY soberStartDate ASC")
    fun getChallengesOrderedByDate(): Flow<List<Challenge>>
}