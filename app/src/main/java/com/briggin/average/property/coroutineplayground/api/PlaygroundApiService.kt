package com.briggin.average.property.coroutineplayground.api

import com.briggin.average.property.coroutineplayground.api.model.*
import java.lang.Exception

private const val SEARCH_TERM_PLAYER = "alan"
private const val SEARCH_TERM_TEAM = "land"
private const val REQUEST_OFFSET = 10L

class PlaygroundApiService(private val api: PlaygroundApi): ApiService {

    override suspend fun getPlayers(): ApiResponse<PlayerModel> {
        val request = RequestBody(SEARCH_TERM_PLAYER, "player", REQUEST_OFFSET)
        return try {
            api.getData(request).results.players?.let { ApiSuccess(it) } ?: ApiError()
        } catch (e: Exception) {
            ApiError()
        }
    }

    override suspend fun getTeams(): ApiResponse<TeamModel> {
        val request = RequestBody(SEARCH_TERM_TEAM, "team", REQUEST_OFFSET)
        return try {
            api.getData(request).results.teams?.let { ApiSuccess(it) } ?: ApiError()
        } catch (e: Exception) {
            ApiError()
        }
    }
}
