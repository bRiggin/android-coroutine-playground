package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.ApiResponse
import com.briggin.coroutineplayground.api.model.PlayerModel
import com.briggin.coroutineplayground.api.model.TeamModel

interface ApiService {
    suspend fun getPlayers(): ApiResponse<PlayerModel>
    suspend fun getTeams(): ApiResponse<TeamModel>
}
