package com.example.rankcheck

import android.content.Intent
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
import com.parse.ParseObject
import com.parse.ParseQuery
import org.w3c.dom.Text

class homeFragment: Fragment()  {
    lateinit var leagueRankUser : TextView
    lateinit var leagueRankUserImage : ImageView
    lateinit var apexRankUser : TextView
    lateinit var apexRankUserImage : ImageView
    lateinit var rocketRankUser : TextView
    lateinit var rocketRankUserImage : ImageView
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
        leagueRankUserImage = itemView.findViewById(R.id.leagueUser)
        apexRankUser = itemView.findViewById(R.id.apexRankTV)
        apexRankUserImage = itemView.findViewById(R.id.apexUser)
        rocketRankUser = itemView.findViewById(R.id.rocketRankTV)
        rocketRankUserImage = itemView.findViewById(R.id.rlUser)

        setUserRanks(leagueRankUser, leagueRankUserImage, "LeagueUsers")
        setUserRanks(apexRankUser, apexRankUserImage, "ApexUsers")
        setUserRanks(rocketRankUser, rocketRankUserImage, "RocketUsers")

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
                var image : Int = 0
                val rank : String = userFound[0].getString("rank").toString()
                if(gameTable == "LeagueUsers")
                    image = leagueSetImage(rank)
                else if(gameTable == "ApexUsers")
                    image = apexSetImage(rank)
                else if(gameTable == "RocketUsers")
                    image = rocketSetImage(rank)
                val updatedFriend = FriendRank(username, image, rank)
                friendRankList.add(updatedFriend)
            }
        }
        return friendRankList
    }

    fun setUserRanks(rankView : TextView, rankImageView: ImageView, gameTable : String){
        val query = ParseQuery.getQuery<ParseObject>(gameTable)
        query.whereContains("RC_username", MainActivity.SESSION_USER)
        val userFound = query.find()
        if(!userFound.isNullOrEmpty()){
            var image : Int = 0
            val rank : String = userFound[0].getString("rank").toString()
            rankView.text = rank
            if(gameTable == "LeagueUsers")
                image = leagueSetImage(rank)
            else if(gameTable == "ApexUsers")
                image = apexSetImage(rank)
            else if(gameTable == "RocketUsers")
                image = rocketSetImage(rank)
            Glide.with(context!!)
                .load(image)
                .override(150, 150)
                .fitCenter()
                .into(rankImageView)
        }
        else{
            rankView.text = "UNLINKED"
            Glide.with(context!!)
                .load(R.drawable.xsymbol)
                .override(150, 150)
                .fitCenter()
                .into(rankImageView)
        }
    }

    fun leagueSetImage(rank : String) : Int {
        var image = R.drawable.xsymbol
        if("Iron" in rank)
            image = R.drawable.league_iron
        else if("Bronze" in rank)
            image = R.drawable.league_bronze
        else if("Silver" in rank)
            image = R.drawable.league_silver
        else if("Gold" in rank)
            image = R.drawable.league_gold
        else if("Platinum" in rank)
            image = R.drawable.league_platinum
        else if("Diamond" in rank)
            image = R.drawable.league_diamond
        else if("Master" in rank)
            image = R.drawable.league_master
        else if("Grandmaster" in rank)
            image = R.drawable.league_grandmaster
        else if("Challenger" in rank)
            image = R.drawable.league_challenger
        return image
    }
    fun apexSetImage(rank : String) : Int {
        var image = R.drawable.xsymbol
        if("Bronze" in rank)
            image = R.drawable.apex_bronze
        else if("Silver" in rank)
            image = R.drawable.apex_silver
        else if("Gold" in rank)
            image = R.drawable.apex_gold
        else if("Platinum" in rank)
            image = R.drawable.apex_platinum
        else if("Diamond" in rank)
            image = R.drawable.apex_diamond
        else if("Master" in rank)
            image = R.drawable.apex_master
        else if("Predator" in rank)
            image = R.drawable.apex_predator
        return image
    }
    fun rocketSetImage(rank : String) : Int {
        var image = R.drawable.xsymbol
        if("Bronze I" == rank)
            image = R.drawable.rocket_bronze1
        else if("Bronze II" == rank)
            image = R.drawable.rocket_bronze2
        else if("Bronze III" == rank)
            image = R.drawable.rocket_bronze3
        else if("Silver I" == rank)
            image = R.drawable.rocket_silver1
        else if("Silver II" == rank)
            image = R.drawable.rocket_silver2
        else if("Silver III" == rank)
            image = R.drawable.rocket_silver3
        else if("Gold I" == rank)
            image = R.drawable.rocket_gold1
        else if("Gold II" == rank)
            image = R.drawable.rocket_gold2
        else if("Gold III" == rank)
            image = R.drawable.rocket_gold3
        else if("Platinum I" == rank)
            image = R.drawable.rocket_platinum1
        else if("Platinum II" == rank)
            image = R.drawable.rocket_platinum2
        else if("Platinum III" == rank)
            image = R.drawable.rocket_platinum3
        else if("Diamond I" == rank)
            image = R.drawable.rocket_diamond1
        else if("Diamond II" == rank)
            image = R.drawable.rocket_diamond2
        else if("Diamond III" == rank)
            image = R.drawable.rocket_diamond3
        else if("Champion I" == rank)
            image = R.drawable.rocket_champ1
        else if("Champion II" == rank)
            image = R.drawable.rocket_champ2
        else if("Champion III" == rank)
            image = R.drawable.rocket_champ3
        else if("Grand Champion I" == rank)
            image = R.drawable.rocket_grandchamp1
        else if("Grand Champion II" == rank)
            image = R.drawable.rocket_grandchamp2
        else if("Grand Champion III" == rank)
            image = R.drawable.rocket_grandchamp3
        else if("Supersonic" == rank)
            image = R.drawable.rocket_super
        return image
    }
}