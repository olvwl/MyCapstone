package com.example.core.utils

import com.example.core.domain.model.Sport
import com.example.core.source.local.entity.SportEntity
import com.example.core.source.local.remote.response.SportResponse

object DataMapper {

    fun mapEntitiesToDomain(input: List<SportEntity>): List<Sport> =
        input.map {
            Sport(
                idSport = it.sportId,
                nameSport = it.name,
                desc = it.description,
                image = it.image ?: "",
                favorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: Sport) = SportEntity(
        sportId = input.idSport,
        name = input.nameSport,
        description = input.desc,
        image = input.image,
        isFavorite = input.favorite
    )

    fun mapResponsesToEntities(input: List<SportResponse>): List<SportEntity> {
        val sportList = ArrayList<SportEntity>()
        input.map {
            val sport = SportEntity(
                sportId = it.id,
                description = it.description,
                name = it.name,
                image = it.image,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }
}