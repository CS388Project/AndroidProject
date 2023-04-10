package com.example.rankcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class gamesFragment: Fragment()  {
    lateinit var gamesRV: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_games_search, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        gamesRV = itemView.findViewById(R.id.gamesFragmentListRv)
        val games = GameFetcher.getGames()
        val adapter = GameAdapter(games)
        gamesRV.adapter = adapter
        gamesRV.layoutManager = LinearLayoutManager(activity)

    }

}