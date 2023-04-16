package com.example.rankcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rankcheck.databinding.FragmentProfilePageBinding

class ProfilePageFragment: Fragment()  {
    lateinit var friendsRV: RecyclerView
    lateinit var friends: MutableList<FriendsList>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        friendsRV = itemView.findViewById(R.id.gamesFragmentListRv)
        friends = FriendFetcher.getFriends()
        val adapter = FriendListAdapter(friendsList)
        friendsRV.adapter = adapter
        friendsRV.layoutManager = GridLayoutManager(this.context, 3)

    }

}