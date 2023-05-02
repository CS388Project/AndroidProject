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
import com.parse.ParseObject
import com.parse.ParseQuery
import org.w3c.dom.Text

class homeFragment: Fragment()  {

    lateinit var leagueRankUser : TextView
    lateinit var apexRankUser : TextView
    lateinit var rocketRankUser : TextView
    lateinit var leagueFriendRV: RecyclerView
    lateinit var apexFriendRV: RecyclerView
    lateinit var rocketFriendRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    private lateinit var leagueFriends: MutableList<FriendRank>
    private lateinit var apexFriends: MutableList<FriendRank>
    private lateinit var rocketFriends: MutableList<FriendRank>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        leagueRankUser = itemView.findViewById(R.id.leagueRankTV)
        apexRankUser = itemView.findViewById(R.id.apexRankTV)
        rocketRankUser = itemView.findViewById(R.id.rocketRankTV)

        setUserRanks(leagueRankUser, "LeagueUsers")
        setUserRanks(apexRankUser, "ApexUsers")
        setUserRanks(rocketRankUser, "RocketUsers")

        leagueFriendRV = itemView.findViewById(R.id.leagueFriendRV)
        apexFriendRV = itemView.findViewById(R.id.apexFriendRV)
        rocketFriendRV = itemView.findViewById(R.id.rocketFriendRV)

        friends = FriendFetcher.getFriends(MainActivity.SESSION_USER)
        leagueFriends = retrieveFriendRanks(friends, "LeagueUsers")
        apexFriends = retrieveFriendRanks(friends, "ApexUsers")
        rocketFriends = retrieveFriendRanks(friends, "RocketUsers")
        val leagueAdapter = FriendRankAdapter(context,leagueFriends,object:FriendRankAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, UserDetail::class.java)
                intent.putExtra(USER_EXTRA, leagueFriends[position].friendUsername)
                context?.startActivity(intent)
            }
        })
        val apexAdapter = FriendRankAdapter(context,apexFriends,object:FriendRankAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, UserDetail::class.java)
                intent.putExtra(USER_EXTRA, apexFriends[position].friendUsername)
                context?.startActivity(intent)
            }
        })
        val rocketAdapter = FriendRankAdapter(context,rocketFriends,object:FriendRankAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, UserDetail::class.java)
                intent.putExtra(USER_EXTRA, rocketFriends[position].friendUsername)
                context?.startActivity(intent)
            }
        })

//        val adapter = FriendListAdapter(it, friendsList)
        leagueFriendRV.adapter = leagueAdapter
        leagueFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        apexFriendRV.adapter = apexAdapter
        apexFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rocketFriendRV.adapter = rocketAdapter
        rocketFriendRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

    fun retrieveFriendRanks(friends : MutableList<FriendsList>, gameTable : String) : MutableList<FriendRank> {
        var friendRankList = mutableListOf<FriendRank>()
        val query = ParseQuery.getQuery<ParseObject>(gameTable)
        for(friend in friends){
            var username = friend.friendUsername
            query.whereContains("RC_username", username)
            val userFound = query.find()
            if(!userFound.isNullOrEmpty()){
                var rank = userFound[0].getString("rank")
                val updatedFriend = FriendRank(username, friend.pfp, rank)
                friendRankList.add(updatedFriend)
            }
        }
        return friendRankList
    }

    fun setUserRanks(view : TextView, gameTable : String){
        val query = ParseQuery.getQuery<ParseObject>(gameTable)
        query.whereContains("RC_username", MainActivity.SESSION_USER)
        val userFound = query.find()
        if(!userFound.isNullOrEmpty()){
            view.text = userFound[0].getString("rank")
        }
        else{
            view.text = "UNLINKED"
        }
    }
}