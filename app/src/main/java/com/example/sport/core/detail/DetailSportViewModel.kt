package com.example.sport.core.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Sport
import com.example.core.domain.usecase.SportUseCase

class DetailSportViewModel(private val sportUseCase: SportUseCase) : ViewModel() {
    companion object{
        private var TAG = "tess"
    }

    fun setFavoriteSport(sport: Sport, newStatus:Boolean) {
        sportUseCase.setFavoriteSport(sport, newStatus)
      //  Log.d(TAG, "tesss")
    }

}