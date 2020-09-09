package com.jason.movietvcatalog.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "release_date")
    var releaseDate: String?,

    @ColumnInfo(name = "runtime")
    var runtime: Int?,

    @ColumnInfo(name = "status")
    var status: String?,

    @ColumnInfo(name = "vote")
    var vote: Float?,

    @ColumnInfo(name = "genre")
    var genre: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "backdrop")
    var backdrop: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
) : Parcelable
