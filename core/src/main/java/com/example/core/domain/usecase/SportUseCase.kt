package com.example.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.core.domain.model.Sport
import com.example.core.source.local.Resource
import java.util.concurrent.Flow

interface SportUseCase {
    fun getAllSport(): kotlinx.coroutines.flow.Flow<Resource<List<Sport>>>
    fun getFavoriteSport(): kotlinx.coroutines.flow.Flow<List<Sport>>
    fun setFavoriteSport(sport: Sport, state: Boolean)
}