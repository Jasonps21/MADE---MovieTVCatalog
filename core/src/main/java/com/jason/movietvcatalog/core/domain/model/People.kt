package com.jason.movietvcatalog.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    var id: Int,
    var name: String,
    var profilePath: String,
    var knownForDepartment: String,
    var popularity: Float
): Parcelable