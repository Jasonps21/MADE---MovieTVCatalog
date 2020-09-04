package com.jason.movietvcatalog.core.data.source.remote.response

data class CreditResponse(
    var id: Int,
    var cast: List<ActorResponse>
)