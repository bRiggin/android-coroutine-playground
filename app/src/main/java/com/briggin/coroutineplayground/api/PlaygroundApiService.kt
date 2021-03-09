package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.*

private const val SEARCH_TERM_PLAYER = "alan"
private const val SEARCH_TERM_TEAM = "land"
private const val REQUEST_OFFSET = 0L

class PlaygroundApiService(private val api: PlaygroundApi) : ApiService {

    override fun getPlayers(): ApiResponse<PlayerModel> {
        val request = RequestBody(SEARCH_TERM_PLAYER, "players", REQUEST_OFFSET)
        return try {
            val response = api.getData(request).execute()
            if (response.isSuccessful) {
                response.body()?.results?.players?.let { ApiSuccess(it) } ?: ApiError()
            } else {
                ApiError()
            }
        } catch (e: Exception) {
            ApiError()
        }
    }

    override fun getTeams(): ApiResponse<TeamModel> {
        val request = RequestBody(SEARCH_TERM_TEAM, "teams", REQUEST_OFFSET)
        return try {
            val response = api.getData(request).execute()
            if (response.isSuccessful) {
                response.body()?.results?.teams?.let { ApiSuccess(it) } ?: ApiError()
            } else {
                ApiError()
            }
        } catch (e: Exception) {
            ApiError()
        }
    }
}
