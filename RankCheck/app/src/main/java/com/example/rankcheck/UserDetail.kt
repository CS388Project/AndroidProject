package com.example.rankcheck

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import com.parse.ParseQuery

class UserDetail: AppCompatActivity(){

    lateinit var profileUsername: TextView
    lateinit var profileImage: ImageView
    lateinit var friendsRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    lateinit var gamesRV: RecyclerView
    lateinit var games: MutableList<DisplayGame>
    lateinit var add: Button
    lateinit var remove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_detail)

        val user =  intent.getStringExtra("USER_EXTRA").toString()

        profileUsername = findViewById(R.id.username)
        profileUsername.text = user

        profileImage = findViewById(R.id.imageUser)
        Glide.with(this)
            .load(R.drawable.img_user)
            .into(profileImage)

        friendsRV = findViewById(R.id.friendsListRV)
        friends = FriendFetcher.getFriends(user)
        val adapter = FriendListAdapter(this, friends)
        friendsRV.adapter = adapter
        friendsRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        gamesRV = findViewById(R.id.gamesListRV)
        games = GameFetcher.getGames()
        val game_adapter = GameCardViewAdapter(this, games)
        gamesRV.adapter = game_adapter
        gamesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        add = findViewById(R.id.addbutton)
        remove = findViewById(R.id.removebutton)

        add.setOnClickListener {

            val newfriend = ParseObject("Friends")
            newfriend.put("username", MainActivity.SESSION_USER)
            newfriend.put("friendUsername", user)
            newfriend.saveInBackground {
                if (it != null){
                    it.localizedMessage?.let { message -> Log.e("AddFriend", message) }
                }else{
                    Log.d("AddFriend","Friend saved.")
                    Toast.makeText(this, "Friend Saved!", Toast.LENGTH_LONG).show()
                }
            }

        }

//        remove.setOnClickListener {
//
//            val query = ParseQuery.getQuery<ParseObject>("Friends")
//            query.whereContains("username", MainActivity.SESSION_USER)
//            var users = query.find()
//
//        }



    }
}