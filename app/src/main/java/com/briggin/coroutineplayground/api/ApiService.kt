package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.ApiResponse
import com.briggin.coroutineplayground.api.model.PlayerModel
import com.briggin.coroutineplayground.api.model.TeamModel
import kotlinx.coroutines.Deferred

interface ApiService {
    suspend fun getPlayersAsync(): Deferred<ApiResponse<PlayerModel>>
    suspend fun getTeamsAsync(): Deferred<ApiResponse<TeamModel>>
}
