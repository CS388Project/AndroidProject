package com.example.rankcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class profileFragment: Fragment()  {
    lateinit var friendsRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    lateinit var gamesRV: RecyclerView
    lateinit var games: MutableList<DisplayGame>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        friendsRV = itemView.findViewById(R.id.friendsListRV)
        friends = FriendFetcher.getFriends()
        val adapter2 = activity?.let { FriendListAdapter(it, friends) }
//        val adapter = FriendListAdapter(it, friendsList)
        friendsRV.adapter = adapter2
        friendsRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        gamesRV = itemView.findViewById(R.id.gamesListRV)
        games = GameFetcher.getGames()
        val game_adapter = activity?.let { GameAdapter(it, games) }
        gamesRV.adapter = game_adapter
        gamesRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

}