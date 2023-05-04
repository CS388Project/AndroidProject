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
import com.bumptech.glide.Glide
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rankcheck.ImageObj.decodeImage
import com.parse.ParseObject
import com.parse.ParseQuery


class UserDetail: AppCompatActivity(){

    lateinit var profileUsername: TextView
    lateinit var profileBio: TextView
    lateinit var profileImage: ImageView
    lateinit var friendsRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    lateinit var gamesRV: RecyclerView
    lateinit var games: MutableList<DisplayGame>
    lateinit var add: Button
    lateinit var remove: Button
    lateinit var cancel: Button
    lateinit var accept: Button
    lateinit var decline: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_detail)

        val user =  intent.getStringExtra("USER_EXTRA").toString()

        profileUsername = findViewById(R.id.username)
        profileUsername.text = user

        val query = ParseQuery.getQuery<ParseObject>("Users")
        query.whereContains("username", user)
        val userDB = query.first
        profileBio = findViewById(R.id.usrBio)
        profileBio.text = userDB.getString("bio")

        profileImage = findViewById(R.id.imageUser)

        if (userDB.getString("image") != null) {
            profileImage.setImageBitmap(decodeImage(userDB.getString("image").toString()))
        }
        else {
            Glide.with(this)
                .load(R.drawable.img_user)
                .into(profileImage)
        }
        friendsRV = findViewById(R.id.friendsListRV)
        friends = FriendFetcher.getFriends(user)
        val adapter = FriendListAdapter(this, friends,object:FriendListAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@UserDetail, UserDetail::class.java)
                if(friends[position].friendUsername != MainActivity.SESSION_USER){
                    intent.putExtra(USER_EXTRA, friends[position].friendUsername)
                    startActivity(intent)
                    finish()
                }

            }
        })
        friendsRV.adapter = adapter
        friendsRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        gamesRV = findViewById(R.id.gamesListRV)
        games = GameFetcher.getGames(user)
        val game_adapter = GameCardViewAdapter(this, games)
        gamesRV.adapter = game_adapter
        gamesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        add = findViewById(R.id.addbutton)
        remove = findViewById(R.id.removebutton)
        cancel = findViewById(R.id.cancelrequest)
        accept = findViewById(R.id.acceptbutton)
        decline = findViewById(R.id.declinebutton)


        if (userFriend(user) == 0)
        {
            add.visibility = View.GONE
            remove.visibility = View.VISIBLE
        }

        if (userFriend(user) == 1)
        {
            add.visibility = View.GONE
            cancel.visibility = View.VISIBLE
        }

        if (userFriend(user) == 3)
        {
            add.visibility = View.GONE
            accept.visibility = View.VISIBLE
            decline.visibility = View.VISIBLE
        }

        add.setOnClickListener {

            val newfriend = ParseObject("Friends")
            newfriend.put("username", MainActivity.SESSION_USER)
            newfriend.put("friendUsername", user)
            newfriend.put("status", "pending")
            newfriend.saveInBackground {
                if (it != null){
                    it.localizedMessage?.let { message -> Log.e("AddFriend", message) }
                }else{
                    Log.d("AddFriend","Request sent")
                    Toast.makeText(this, "Request sent!", Toast.LENGTH_LONG).show()
                }
            }
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }

        cancel.setOnClickListener {

            val query = ParseQuery.getQuery<ParseObject>("Friends")
            query.whereContains("username", MainActivity.SESSION_USER)
            var users = query.find()
            if (!users.isNullOrEmpty()) {
                for( usr in users.iterator())
                {
                    if(usr.getString("friendUsername").toString() == user)
                    {
                        usr.deleteInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("RemoveFriend", message) }
                            }else{
                                Log.d("RemoveFriend","Friend Removed.")
                                Toast.makeText(this, "Friend Removed!", Toast.LENGTH_LONG).show()
                            }
                        }
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
            }


        }

        remove.setOnClickListener {

            val query = ParseQuery.getQuery<ParseObject>("Friends")
            val query2 = ParseQuery.getQuery<ParseObject>("Friends")
            query.whereContains("username", MainActivity.SESSION_USER)
            var users = query.find()
            if (!users.isNullOrEmpty()) {
                for( usr in users.iterator())
                {
                    if(usr.getString("friendUsername").toString() == user)
                    {
                        usr.deleteInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("RemoveFriend", message) }
                            }else{
                                Log.d("RemoveFriend","Friend Removed.")
                                Toast.makeText(this, "Friend Removed!", Toast.LENGTH_LONG).show()
                            }
                        }
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
            }
            query2.whereContains("username", user)
            users = query2.find()
            if (!users.isNullOrEmpty()) {
                for( usr in users.iterator())
                {
                    if(usr.getString("friendUsername").toString() == MainActivity.SESSION_USER)
                    {
                        usr.deleteInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("RemoveFriend", message) }
                            }else{
                                Log.d("RemoveFriend","Friend Removed.")
                                Toast.makeText(this, "Friend Removed!", Toast.LENGTH_LONG).show()
                            }
                        }
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
            }

        }

        accept.setOnClickListener {
            val newfriend = ParseQuery.getQuery<ParseObject>("Friends")
            newfriend.whereEqualTo("friendUsername", MainActivity.SESSION_USER)
            newfriend.whereEqualTo("username",user)
            var find = newfriend.find()
            find.first().put("status", "friends")
            find.first().saveInBackground {
                if (it != null){
                    it.localizedMessage?.let { message -> Log.e("AddFriend", message) }
                }else{
                    Log.d("AddFriend","failed")
                }
            }
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }


    private fun userFriend(usr: String): Int
    {
        val query = ParseQuery.getQuery<ParseObject>("Friends")
        val query2 = ParseQuery.getQuery<ParseObject>("Friends")
        query.whereEqualTo("username", MainActivity.SESSION_USER)
        var users = query.find()

        if (!users.isNullOrEmpty()) {
            for( user in users.iterator())
            {
                if(user.getString("friendUsername").toString() == usr && user.getString("status").toString() == "friends")
                {
                    return 0
                }
                if(user.getString("friendUsername").toString() == usr && user.getString("status").toString() == "pending")
                {
                    return 1
                }
            }
        }
        query2.whereEqualTo("friendUsername", MainActivity.SESSION_USER)
        users = query2.find()
        if (!users.isNullOrEmpty()) {
            for( user in users.iterator())
            {
                if(user.getString("username").toString() == usr && user.getString("status").toString() == "friends")
                {
                    return 0
                }
                if(user.getString("username").toString() == usr && user.getString("status").toString() == "pending")
                {
                    return 3
                }
            }
        }
        return 2
    }

    private fun getpending(usr: String): Int
    {
        val query = ParseQuery.getQuery<ParseObject>("Friends")
        val query2 = ParseQuery.getQuery<ParseObject>("Friends")
        query.whereEqualTo("username", MainActivity.SESSION_USER)
        var users = query.find()

        if (!users.isNullOrEmpty()) {
            for( user in users.iterator())
            {
                if(user.getString("friendUsername").toString() == usr && user.getString("status").toString() == "friends")
                {
                    return 0
                }
                if(user.getString("friendUsername").toString() == usr && user.getString("status").toString() == "pending")
                {
                    return 1
                }
            }
        }
        query2.whereEqualTo("friendUsername", MainActivity.SESSION_USER)
        users = query2.find()
        if (!users.isNullOrEmpty()) {
            for( user in users.iterator())
            {
                if(user.getString("username").toString() == usr && user.getString("status").toString() == "friends")
                {
                    return 0
                }
                if(user.getString("username").toString() == usr && user.getString("status").toString() == "pending")
                {
                    return 1
                }
            }
        }
        return 2
    }

}