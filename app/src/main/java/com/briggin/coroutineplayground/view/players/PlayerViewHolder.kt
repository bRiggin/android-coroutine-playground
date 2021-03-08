package com.briggin.coroutineplayground.view.players

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.briggin.coroutineplayground.api.model.PlayerModel
import kotlinx.android.synthetic.main.view_holder_player.view.*

class PlayerViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(model: PlayerModel) {
        view.playerName.text = "${model.playerFirstName} ${model.playerSecondName}"
        view.playerClub.text = model.playerClub
    }
}