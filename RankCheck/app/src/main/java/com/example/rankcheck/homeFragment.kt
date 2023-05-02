package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class homeFragment: Fragment()  {

    lateinit var leagueFriendRV: RecyclerView
    lateinit var apexFriendRV: RecyclerView
    lateinit var rocketFriendRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        leagueFriendRV = itemView.findViewById(R.id.leagueFriendRV)
        apexFriendRV = itemView.findViewById(R.id.apexFriendRV)
        rocketFriendRV = itemView.findViewById(R.id.rocketFriendRV)

        friends = FriendFetcher.getFriends(MainActivity.SESSION_USER)
        val adapter = FriendListAdapter(context,friends,object:FriendListAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, UserDetail::class.java)
                intent.putExtra(USER_EXTRA, friends[position].friendUsername)
                context?.startActivity(intent)
            }
        })

//        val adapter = FriendListAdapter(it, friendsList)
        leagueFriendRV.adapter = adapter
        leagueFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        apexFriendRV.adapter = adapter
        apexFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rocketFriendRV.adapter = adapter
        rocketFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }
}