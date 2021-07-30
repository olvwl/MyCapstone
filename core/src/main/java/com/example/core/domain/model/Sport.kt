package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val idSport : String,
    val nameSport : String,
    val desc : String,
    val image : String,
    val favorite : Boolean
): Parcelable
