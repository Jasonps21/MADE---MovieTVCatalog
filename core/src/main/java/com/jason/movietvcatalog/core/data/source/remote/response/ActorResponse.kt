package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @field:SerializedName("character")
    var character: String,
    @field:SerializedName("profile_path")
    var profile_path: String?,
    @field:SerializedName("name")
    var name: String
)