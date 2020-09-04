package com.jason.movietvcatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvshowResponse(
    @field:SerializedName("results")
    var results: List<TvResponse>
)