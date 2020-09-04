package com.jason.movietvcatalog.core.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    var id: Int,
    var posterPath: String?
) : Parcelable