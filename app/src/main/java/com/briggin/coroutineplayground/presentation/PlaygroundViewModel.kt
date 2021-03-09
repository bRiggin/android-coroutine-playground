package com.briggin.coroutineplayground.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briggin.coroutineplayground.api.ApiService
import com.briggin.coroutineplayground.api.model.ApiError
import com.briggin.coroutineplayground.api.model.ApiSuccess
import com.briggin.coroutineplayground.api.model.PlayerModel
import com.briggin.coroutineplayground.api.model.TeamModel

class PlaygroundViewModel(private val api: ApiService) : ViewModel() {

    private val _players: MutableLiveData<List<PlayerModel>> = MutableLiveData()
    val players: LiveData<List<PlayerModel>> get() = _players

    private val _teams: MutableLiveData<List<TeamModel>> = MutableLiveData()
    val teams: LiveData<List<TeamModel>> get() = _teams

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun viewLoaded() {
        val playerTriggerTimestamp = System.currentTimeMillis()
        Log.wtf("TAG", "Performing players request")
        api.getPlayers() { response ->
            when (response) {
                is ApiSuccess -> {
                    Log.wtf("TAG", "Players received after: ${System.currentTimeMillis() - playerTriggerTimestamp}")
                    _players.postValue(response.items)
                }
                is ApiError -> {
                    Log.wtf("TAG", "Players error occurred after: ${System.currentTimeMillis() - playerTriggerTimestamp}")
                    _error.postValue("Error occurred fetching players")
                }
            }
        }

        val teamsTriggerTimestamp = System.currentTimeMillis()
        Log.wtf("TAG", "Performing teams request")
        api.getTeams() { response ->
            when (response) {
                is ApiSuccess -> {
                    Log.wtf("TAG", "Teams received after: ${System.currentTimeMillis() - teamsTriggerTimestamp}")
                    _teams.postValue(response.items)
                }
                is ApiError -> {
                    Log.wtf("TAG", "Teams error occurred after: ${System.currentTimeMillis() - playerTriggerTimestamp}")
                    _error.postValue("Error occurred fetching teams")
                }
            }
        }
    }
}
