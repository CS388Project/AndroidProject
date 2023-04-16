package com.example.rankcheck;

public class FriendFetcher {
    companion object{
        val friendsNames = listOf("Friend1", "Friend2", "Friend3", "Friend4", "Friend5")

        val friendPFP = listOf(
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user,
            R.drawable.img_user)
        fun getFriends(): MutableList<FriendsList> {
            var friends : MutableList<FriendsList> = ArrayList()
            for (i in 0..2) {
                val friend = FriendsList(friendsNames[i], friendPFP[i])
                friends.add(friend)
            }
            return friends


        }
    }
}
