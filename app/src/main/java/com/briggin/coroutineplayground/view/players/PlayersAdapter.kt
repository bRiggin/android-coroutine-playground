package com.briggin.coroutineplayground.view.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briggin.coroutineplayground.R
import com.briggin.coroutineplayground.api.model.PlayerModel

class PlayersAdapter: RecyclerView.Adapter<PlayerViewHolder>() {

    private var models: List<PlayerModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder = PlayerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_player, parent, false)
    )

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount(): Int = models.size

    fun updateView(models: List<PlayerModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}
