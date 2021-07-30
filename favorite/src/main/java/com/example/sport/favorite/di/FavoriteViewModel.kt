package com.example.sport.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.SportUseCase

class FavoriteViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val favoriteSport = sportUseCase.getFavoriteSport().asLiveData()
}


