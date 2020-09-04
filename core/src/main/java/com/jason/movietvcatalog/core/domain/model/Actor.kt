package com.jason.movietvcatalog.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor (
    var id: Int = 0,
    var character: String,
    var profilePath: String?,
    var name: String,
    var movieId: Int
): Parcelable