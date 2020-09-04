@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection")

package com.jason.movietvcatalog.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Parcelize
@Entity(
    tableName = "actorentities",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"]
    )],
    indices = [Index(value = ["actorId"]),
        Index(value = ["movieId"])]
)
class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "actorId")
    var id: Int = 0,
    @ColumnInfo(name= "character")
    @SerializedName("character")
    var character: String,
    @ColumnInfo(name= "profile_path")
    @SerializedName("profile_path")
    var profilePath: String?,
    @ColumnInfo(name= "name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo(name= "movieId")
    @SerializedName("movie_id")
    var movieId: Int
): Parcelable