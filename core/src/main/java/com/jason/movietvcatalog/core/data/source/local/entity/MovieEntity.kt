package com.jason.movietvcatalog.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    var runtime: Int?,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status: String?,

    @ColumnInfo(name = "vote")
    @SerializedName("vote")
    var vote: Float?,

    @ColumnInfo(name = "genre")
    @SerializedName("genre")
    var genre: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String?,

    @ColumnInfo(name = "backdrop")
    @SerializedName("backdrop")
    var backdrop: String?,

    @ColumnInfo(name = "category")
    @SerializedName("category")
    var category: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
