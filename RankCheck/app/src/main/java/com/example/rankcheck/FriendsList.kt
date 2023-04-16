package com.example.rankcheck

var friendsList = mutableListOf<FriendsList>()
class FriendsList(
    val friendUsername: String?,
    val pfp: Int,
    val id: Int? = friendsList.size
) : java.io.Serializable {

}