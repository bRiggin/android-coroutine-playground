package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

private const val SEARCH_TERM_PLAYER = "alan"
private const val SEARCH_TERM_TEAM = "land"
private const val REQUEST_OFFSET = 0L

class PlaygroundApiService(private val api: PlaygroundApi) : ApiService {

    override suspend fun getPlayersAsync(): Deferred<ApiResponse<PlayerModel>> = coroutineScope {
        async {
            val request = RequestBody(SEARCH_TERM_PLAYER, "players", REQUEST_OFFSET)
            try {
                api.getData(request).results.players?.let { ApiSuccess(it) } ?: ApiError()
            } catch (e: Exception) {
                ApiError()
            }
        }
    }

    override suspend fun getTeamsAsync(): Deferred<ApiResponse<TeamModel>> = coroutineScope {
        async {
            val request = RequestBody(SEARCH_TERM_TEAM, "teams", REQUEST_OFFSET)
            try {
                api.getData(request).results.teams?.let { ApiSuccess(it) } ?: ApiError()
            } catch (e: Exception) {
                ApiError()
            }
        }
    }
}
