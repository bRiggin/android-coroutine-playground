package com.briggin.average.property.coroutineplayground.api

import com.briggin.average.property.coroutineplayground.api.model.ApiResponse
import com.briggin.average.property.coroutineplayground.api.model.PlayerModel
import com.briggin.average.property.coroutineplayground.api.model.TeamModel

interface ApiService {
    suspend fun getPlayers(): ApiResponse<PlayerModel>
    suspend fun getTeams(): ApiResponse<TeamModel>
}
