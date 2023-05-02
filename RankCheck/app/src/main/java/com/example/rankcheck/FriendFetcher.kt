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
            val query2= ParseQuery.getQuery<ParseObject>("Friends")
            val username = username.toString()
            var friends = mutableListOf<FriendsList>()

            query.whereEqualTo("username", username)
            var ParseFriends = query.find()

            if (!ParseFriends.isNullOrEmpty())
            {
            for(ParseFriend in ParseFriends){
                if (ParseFriend.getString("status") == "friends") {
                    var newFriend =
                        FriendsList(ParseFriend.getString("friendUsername"), R.drawable.img_user)
                    friends.add(newFriend)
                }
            }}

            query2.whereEqualTo("friendUsername", username)
            ParseFriends = query2.find()

            if (!ParseFriends.isNullOrEmpty())
            {
            for(ParseFriend in ParseFriends){
                if (ParseFriend.getString("status") == "friends") {
                    var newFriend = FriendsList(ParseFriend.getString("username"), R.drawable.img_user)
                    friends.add(newFriend)
                }
            }}

            return friends
        }
    }
}
