package com.briggin.average.property.coroutineplayground.api.model

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("result")
    val results: ResultsModel
)

data class ResultsModel(
    @SerializedName("teams")
    val teams: List<TeamModel>?,
    @SerializedName("players")
    val players: List<PlayerModel>?
)
