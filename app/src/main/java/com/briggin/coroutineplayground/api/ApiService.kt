package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.ApiResponse
import com.briggin.coroutineplayground.api.model.PlayerModel
import com.briggin.coroutineplayground.api.model.TeamModel

interface ApiService {
    fun getPlayers(): ApiResponse<PlayerModel>
    fun getTeams(): ApiResponse<TeamModel>
}
