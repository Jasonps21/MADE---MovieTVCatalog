package com.jason.movietvcatalog.core.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable

data class TvResponse(
    var id: String?,
    var name: String?,
    var poster_path: String?,
    var first_air_date: String?,
    var episode_run_time: ArrayList<Int>?,
    var status: String?,
    var vote_average: Float?,
    var genres: List<GenreResponse>?,
    var overview: String?,
    var backdrop_path: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        arrayListOf<Int>().apply {
            parcel.readList(this as List<*>, Integer::class.java.classLoader)
        },
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        arrayListOf<GenreResponse>().apply {
            parcel.readList(this as List<*>, GenreResponse::class.java.classLoader)
        },
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(poster_path)
        parcel.writeString(first_air_date)
        parcel.writeString(status)
        parcel.writeValue(vote_average)
        parcel.writeString(overview)
        parcel.writeString(backdrop_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvResponse> {
        override fun createFromParcel(parcel: Parcel): TvResponse {
            return TvResponse(parcel)
        }

        override fun newArray(size: Int): Array<TvResponse?> {
            return arrayOfNulls(size)
        }
    }

}