package com.briggin.coroutineplayground.view.teams

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.briggin.coroutineplayground.api.model.TeamModel
import kotlinx.android.synthetic.main.view_holder_team.view.*

class TeamViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(model: TeamModel) {
        view.teamName.text = model.teamName
        view.teamStadium.text = model.teamStadium
    }
}
