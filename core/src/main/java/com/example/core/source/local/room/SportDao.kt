package com.example.core.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.source.local.entity.SportEntity
import java.util.concurrent.Flow

@Dao
interface SportDao {
    @Query("SELECT * FROM sport")
    fun getAllSport(): kotlinx.coroutines.flow.Flow<List<SportEntity>>

    @Query("SELECT * FROM sport where isFavorite = 1")
    fun getFavoriteSport(): kotlinx.coroutines.flow.Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(sport: List<SportEntity>)

    @Update
    fun updateFavoriteSport(sport: SportEntity)
}