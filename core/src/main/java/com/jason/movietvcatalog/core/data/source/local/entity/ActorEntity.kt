package com.jason.movietvcatalog.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

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
    var character: String,
    @ColumnInfo(name= "profile_path")
    var profilePath: String?,
    @ColumnInfo(name= "name")
    var name: String,
    @ColumnInfo(name= "movieId")
    var movieId: Int
): Parcelable