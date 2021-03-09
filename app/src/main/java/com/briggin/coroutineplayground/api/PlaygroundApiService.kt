package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SEARCH_TERM_PLAYER = "alan"
private const val SEARCH_TERM_TEAM = "land"
private const val REQUEST_OFFSET = 0L

class PlaygroundApiService(private val api: PlaygroundApi) : ApiService {

    override fun getPlayers(block: (ApiResponse<PlayerModel>) -> Unit) {
        val request = RequestBody(SEARCH_TERM_PLAYER, "players", REQUEST_OFFSET)

        api.getData(request).enqueue(object : Callback<ApiModel>{
            override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                if (response.isSuccessful) {
                    block(response.body()?.results?.players?.let { ApiSuccess(it) } ?: ApiError())
                } else {
                    block(ApiError())
                }
            }
            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                block(ApiError())
            }
        })
    }

    override fun getTeams(block: (ApiResponse<TeamModel>) -> Unit) {
        val request = RequestBody(SEARCH_TERM_TEAM, "teams", REQUEST_OFFSET)
        api.getData(request).enqueue(object : Callback<ApiModel>{
            override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                if (response.isSuccessful) {
                    block(response.body()?.results?.teams?.let { ApiSuccess(it) } ?: ApiError())
                } else {
                    block(ApiError())
                }
            }
            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                block(ApiError())
            }
        })
    }
}
