package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPeopleResponse(
    @field:SerializedName("results")
    var results: List<PeopleResponse>
)