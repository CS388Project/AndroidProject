package com.example.rankcheck

import android.media.Image
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
import com.example.rankcheck.MainActivity.Companion.SESSION_USER
import com.parse.ParseObject
import org.w3c.dom.Text
import java.util.Arrays

class profileFragment: Fragment()  {
    lateinit var profileUsername: TextView
    lateinit var profileImage: ImageView
    lateinit var friendsRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    lateinit var gamesRV: RecyclerView
    lateinit var games: MutableList<DisplayGame>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        profileUsername = itemView.findViewById(R.id.username)
        profileUsername.text = SESSION_USER

        profileImage = itemView.findViewById(R.id.imageUser)
        Glide.with(itemView.context)
            .load(R.drawable.img_user)
            .into(profileImage)

        friendsRV = itemView.findViewById(R.id.friendsListRV)
        friends = FriendFetcher.getFriends(SESSION_USER)
        val adapter = FriendListAdapter(context,friends,object:FriendListAdapter.SetOnItemClickListener{
            override fun onItemClick() {

                Toast.makeText(context, "Clicked!",Toast.LENGTH_SHORT).show()

            }
        })
//        val adapter = FriendListAdapter(it, friendsList)
        friendsRV.adapter = adapter
        friendsRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        gamesRV = itemView.findViewById(R.id.gamesListRV)
        games = GameFetcher.getGames()
        val game_adapter =  GameCardViewAdapter(context, games, object:GameCardViewAdapter.SetOnItemClickListener{
            override fun onItemClick() {
                Toast.makeText(context, "Clicked!",Toast.LENGTH_SHORT).show()
            }
        })
        gamesRV.adapter = game_adapter
        gamesRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

}