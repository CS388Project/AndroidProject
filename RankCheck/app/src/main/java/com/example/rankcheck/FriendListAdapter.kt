package com.example.rankcheck

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rankcheck.databinding.FriendsCardCellBinding
import com.example.rankcheck.databinding.FriendsDetailBinding

class FriendListAdapter(private val friends: List<FriendsList>) : RecyclerView.Adapter<CardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FriendsCardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.findFriend(friends[position])
    }
}