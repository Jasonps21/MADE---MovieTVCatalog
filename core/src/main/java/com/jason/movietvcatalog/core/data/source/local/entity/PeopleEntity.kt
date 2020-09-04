package com.jason.movietvcatalog.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "peopleentities")
class PeopleEntity(
    @PrimaryKey
    @ColumnInfo(name = "peopleId")
    var id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo(name = "profile_path")
    @SerializedName("profile_path")
    var profilePath: String,
    @ColumnInfo(name = "known_for_department")
    @SerializedName("known_for_department")
    var knownForDepartment: String,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Float
) : Parcelable