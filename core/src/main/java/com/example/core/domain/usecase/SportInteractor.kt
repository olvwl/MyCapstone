package com.example.core.domain.usecase

import com.example.core.domain.model.Sport
import com.example.core.domain.repository.ISportRepository


class SportInteractor(private val sportRepository: ISportRepository): SportUseCase {

    override fun getAllSport() = sportRepository.getAllSport()

    override fun getFavoriteSport() = sportRepository.getFavoriteSport()

    override fun setFavoriteSport(sport: Sport, state: Boolean) = sportRepository.setFavoriteSport(sport, state)
}