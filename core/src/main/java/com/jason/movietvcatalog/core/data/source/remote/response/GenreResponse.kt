package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("name")
    var name: String
)