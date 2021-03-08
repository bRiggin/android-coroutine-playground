package com.briggin.coroutineplayground.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briggin.coroutineplayground.api.ApiService
import com.briggin.coroutineplayground.api.PlaygroundApi
import com.briggin.coroutineplayground.api.model.ApiError
import com.briggin.coroutineplayground.api.model.ApiSuccess
import com.briggin.coroutineplayground.api.model.PlayerModel
import com.briggin.coroutineplayground.api.model.TeamModel

class PlaygroundViewModel(private val api: ApiService): ViewModel() {

    private val _players: MutableLiveData<List<PlayerModel>> = MutableLiveData()
    val players: LiveData<List<PlayerModel>> get() = _players

    private val _teams: MutableLiveData<List<TeamModel>> = MutableLiveData()
    val teams: LiveData<List<TeamModel>> get() = _teams

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    suspend fun viewLoaded() {
        when(val response = api.getPlayers()) {
            is ApiSuccess -> _players.postValue(response.items)
            is ApiError -> _error.postValue("Error occurred fetching players")
        }

        when(val response = api.getTeams()) {
            is ApiSuccess -> _teams.postValue(response.items)
            is ApiError -> _error.postValue("Error occurred fetching teams")
        }
    }
}
