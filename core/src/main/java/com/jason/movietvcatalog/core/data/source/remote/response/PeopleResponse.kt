package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("name")
    var name: String,
    @field:SerializedName("profile_path")
    var profilePath: String,
    @field:SerializedName("known_for_department")
    var knownForDepartment: String,
    @field:SerializedName("popularity")
    var popularity: Float
)