package com.jason.movietvcatalog.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var name: String?,
    var posterPath: String?,
    var releaseDate: String?,
    var runtime: Int?,
    var status: String?,
    var vote: Float?,
    var genre: String?,
    var overview: String?,
    var backdrop: String?,
    var category: String?,
    var isFavorite: Boolean
) : Parcelable
