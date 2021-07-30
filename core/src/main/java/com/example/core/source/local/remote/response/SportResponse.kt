package com.example.core.source.local.remote.response

import com.google.gson.annotations.SerializedName

data class SportResponse(
    @field:SerializedName("idSport")
    val id: String,

    @field:SerializedName("strSport")
    val name: String,

    @field:SerializedName("strSportDescription")
    val description: String,

    @field:SerializedName("strSportThumb")
    val image: String
)
