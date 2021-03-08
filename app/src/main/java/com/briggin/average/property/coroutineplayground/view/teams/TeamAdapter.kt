package com.briggin.average.property.coroutineplayground.view.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briggin.average.property.coroutineplayground.R
import com.briggin.average.property.coroutineplayground.api.model.TeamModel

class TeamAdapter: RecyclerView.Adapter<TeamViewHolder>() {

    private var models: List<TeamModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_team, parent, false)
    )

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount(): Int = models.size

    fun updateView(models: List<TeamModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}
