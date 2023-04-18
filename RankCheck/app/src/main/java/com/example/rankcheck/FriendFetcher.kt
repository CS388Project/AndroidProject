package com.example.rankcheck;

import android.util.Log
import com.parse.ParseObject
import com.parse.ParseQuery

class FriendFetcher {
    companion object{
        val friendsNames = listOf("Friend1", "Friend2", "Friend3", "Friend4", "Friend5")
        val friendPFP = listOf(
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user)
        fun getFriends(username: String): MutableList<FriendsList> {
            val query = ParseQuery.getQuery<ParseObject>("Friends")
            val username = username.toString()

            query.whereContains("username", username)
            val ParseFriends = query.find()

            var friends = mutableListOf<FriendsList>()
            for(ParseFriend in ParseFriends){
                var newFriend = FriendsList(ParseFriend.getString("friendUsername"), R.drawable.img_user)
                friends.add(newFriend)
            }
            return friends
        }
    }
}
