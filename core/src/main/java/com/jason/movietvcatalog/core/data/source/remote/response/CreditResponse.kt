package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("cast")
    var cast: List<ActorResponse>
)