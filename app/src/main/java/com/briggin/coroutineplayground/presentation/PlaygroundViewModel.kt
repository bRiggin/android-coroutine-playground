package com.briggin.coroutineplayground.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briggin.coroutineplayground.api.ApiService
import com.briggin.coroutineplayground.api.model.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

class PlaygroundViewModel(private val api: ApiService): ViewModel() {

    private val _players: MutableLiveData<List<PlayerModel>> = MutableLiveData()
    val players: LiveData<List<PlayerModel>> get() = _players

    private val _teams: MutableLiveData<List<TeamModel>> = MutableLiveData()
    val teams: LiveData<List<TeamModel>> get() = _teams

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    suspend fun viewLoaded() {
        val playerTriggerTimestamp = System.currentTimeMillis()
        Log.wtf("TAG", "Performing players request")
        val teamsTriggerTimestamp = System.currentTimeMillis()
        Log.wtf("TAG", "Performing teams request")

        val response = listOf(api.getPlayersAsync(), api.getTeamsAsync()).awaitAll()

        when(val playerResponse = (response.first() as ApiResponse<PlayerModel>)) {
            is ApiSuccess -> {
                Log.wtf("TAG", "Players received after: ${System.currentTimeMillis() - playerTriggerTimestamp}")
                _players.postValue(playerResponse.items)
            }
            is ApiError -> {
                Log.wtf("TAG", "Players error occurred after: ${System.currentTimeMillis() - playerTriggerTimestamp}")
                _error.postValue("Error occurred fetching players")
            }
        }

        when(val teamsResponse = (response.last() as ApiResponse<TeamModel>)) {
            is ApiSuccess -> {
                Log.wtf("TAG", "Teams received after: ${System.currentTimeMillis() - teamsTriggerTimestamp}")
                _teams.postValue(teamsResponse.items)
            }
            is ApiError -> {
                Log.wtf("TAG", "Teams error occurred after: ${System.currentTimeMillis() - teamsTriggerTimestamp}")
                _error.postValue("Error occurred fetching teams")
            }
        }
    
}
