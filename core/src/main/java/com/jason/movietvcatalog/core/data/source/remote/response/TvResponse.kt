package com.jason.movietvcatalog.core.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("first_air_date")
    var first_air_date: String?,
    @SerializedName("vote_average")
    var vote_average: Float?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(poster_path)
        parcel.writeString(first_air_date)
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