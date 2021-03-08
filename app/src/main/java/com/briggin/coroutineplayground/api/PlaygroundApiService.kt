package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.*
import java.lang.Exception

private const val SEARCH_TERM_PLAYER = "alan"
private const val SEARCH_TERM_TEAM = "land"
private const val REQUEST_OFFSET = 0L

class PlaygroundApiService(private val api: PlaygroundApi): ApiService {

    override suspend fun getPlayers(): ApiResponse<PlayerModel> {
        val request = RequestBody(SEARCH_TERM_PLAYER, "players", REQUEST_OFFSET)
        return try {
            api.getData(request).results.players?.let { ApiSuccess(it) } ?: ApiError()
        } catch (e: Exception) {
            ApiError()
        }
    }

    override suspend fun getTeams(): ApiResponse<TeamModel> {
        val request = RequestBody(SEARCH_TERM_TEAM, "teams", REQUEST_OFFSET)
        return try {
            api.getData(request).results.teams?.let { ApiSuccess(it) } ?: ApiError()
        } catch (e: Exception) {
            ApiError()
        }
    }
}
