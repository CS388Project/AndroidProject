package com.example.rankcheck

import androidx.recyclerview.widget.RecyclerView
import com.example.rankcheck.databinding.FriendsCardCellBinding

class CardViewHolder(
    private val cardCellBinding: FriendsCardCellBinding
): RecyclerView.ViewHolder(cardCellBinding.root) {
    fun findFriend(friend: FriendsList){
        cardCellBinding.friendName.text = friend.friendUsername
        friend.pfp?.let { cardCellBinding.profilePic.setImageResource(it) }
    }
}