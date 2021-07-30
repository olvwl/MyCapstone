package com.example.core.source

import androidx.lifecycle.LiveData
import com.example.core.source.local.entity.SportEntity
import com.example.core.source.local.room.SportDao
import java.util.concurrent.Flow


class LocalDataSource (private val sportDao: SportDao) {

    fun getAllSport(): kotlinx.coroutines.flow.Flow<List<SportEntity>> = sportDao.getAllSport()

    fun getFavoriteSport(): kotlinx.coroutines.flow.Flow<List<SportEntity>> = sportDao.getFavoriteSport()

    suspend fun insertSport(sportList: List<SportEntity>) = sportDao.insertSport(sportList)

    fun setFavoriteSport(sport: SportEntity, newState: Boolean) {
        sport.isFavorite = newState
        sportDao.updateFavoriteSport(sport)
    }
}