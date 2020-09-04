package com.jason.movietvcatalog.core.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("vote_average")
    var voteAverage: Float?,
    @SerializedName("genres")
    var genres: List<GenreResponse>?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Float,
        arrayListOf<GenreResponse>().apply {
            parcel.readList(this as List<*>, GenreResponse::class.java.classLoader)
        },
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeValue(runtime)
        parcel.writeString(status)
        parcel.writeValue(voteAverage)
        parcel.writeString(overview)
        parcel.writeString(backdropPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResponse> {
        override fun createFromParcel(parcel: Parcel): MovieResponse {
            return MovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieResponse?> {
            return arrayOfNulls(size)
        }
    }


}