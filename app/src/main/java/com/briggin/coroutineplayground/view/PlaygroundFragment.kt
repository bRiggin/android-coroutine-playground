package com.briggin.coroutineplayground.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.briggin.coroutineplayground.R
import com.briggin.coroutineplayground.presentation.PlaygroundViewModel
import com.briggin.coroutineplayground.view.players.PlayersAdapter
import com.briggin.coroutineplayground.view.teams.TeamAdapter
import kotlinx.android.synthetic.main.fragment_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaygroundFragment : Fragment(R.layout.fragment_playground) {

    private val viewModel: PlaygroundViewModel by viewModel()

    private val playgroundLayoutManger: RecyclerView.LayoutManager
        get() = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRecyclerViews()
        GlobalScope.launch { viewModel.loadPlayers() }
        GlobalScope.launch { viewModel.loadTeams() }
    }

    private fun initObservers() {
        viewModel.players.observe(viewLifecycleOwner, {
            (playersRecyclerView.adapter as? PlayersAdapter)?.updateView(it)
        })
        viewModel.teams.observe(viewLifecycleOwner, {
            (teamsRecyclerView.adapter as? TeamAdapter)?.updateView(it)
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initRecyclerViews() {
        playersRecyclerView.apply {
            layoutManager = playgroundLayoutManger
            adapter = PlayersAdapter()
        }
        teamsRecyclerView.apply {
            layoutManager = playgroundLayoutManger
            adapter = TeamAdapter()
        }
    }

    companion object {
        fun newInstance() = PlaygroundFragment()
    }
}
