package com.example.rankcheck;

import android.util.Log
import com.parse.ParseObject
import com.parse.ParseQuery

class FriendFetcher {
    companion object{
        fun getFriends(username: String): MutableList<FriendsList> {
            val query = ParseQuery.getQuery<ParseObject>("Friends")
            val query2= ParseQuery.getQuery<ParseObject>("Friends")
            val query3 = ParseQuery.getQuery<ParseObject>("Users")
            val query4 = ParseQuery.getQuery<ParseObject>("Users")
            val username = username.toString()
            var friends = mutableListOf<FriendsList>()


            query.whereEqualTo("username", username)
            var ParseFriends = query.find()

            if (!ParseFriends.isNullOrEmpty())
            {
            for(ParseFriend in ParseFriends){
                if (ParseFriend.getString("status") == "friends") {
                    query3.whereEqualTo("username", ParseFriend.getString("friendUsername"))
                    var userPFP = query3.find()

                    var newFriend =
                        FriendsList(ParseFriend.getString("friendUsername"), userPFP.first().getString("image").toString())
                    friends.add(newFriend)
                }
                Log.e("fetch", ParseFriend.getString("image").toString())
            }}

            query2.whereEqualTo("friendUsername", username)
            ParseFriends = query2.find()

            if (!ParseFriends.isNullOrEmpty())
            {
            for(ParseFriend in ParseFriends){

                if (ParseFriend.getString("status") == "friends") {
                    query4.whereEqualTo("username", ParseFriend.getString("username"))
                    var userPFP2 = query4.find()

                    var newFriend = FriendsList(ParseFriend.getString("username"), userPFP2.first().getString("image").toString())
                    friends.add(newFriend)
                }
            }}

            return friends
        }
    }
}
