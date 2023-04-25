package com.example.rankcheck


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rankcheck.MainActivity.Companion.SESSION_USER
import com.parse.ParseObject
import com.parse.ParseQuery


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
        val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034

        val editBtn = itemView.findViewById<Button>(R.id.editBtn)
        var submitBtn = itemView.findViewById<Button>(R.id.editSubmit)

        var bioTV = itemView.findViewById<TextView>(R.id.userBio)
        var editBioET = itemView.findViewById<EditText>(R.id.editBioET)

        // Checks for the username that is set in textview to grab right bio
        val userLookup = profileUsername.text.toString()
        val query = ParseQuery.getQuery<ParseObject>("Users")
        query.whereContains("username", userLookup)
        val userDB = query.first
        bioTV.text = userDB.getString("bio")
        profileImage.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        }
        editBtn.setOnClickListener{
            editBtn.visibility = View.GONE
            submitBtn.visibility = View.VISIBLE

            bioTV.visibility = View.GONE

            var currentBio = bioTV.text.toString()
            editBioET.setText(currentBio)
            Toast.makeText(context, currentBio, Toast.LENGTH_SHORT).show()

            editBioET.visibility = View.VISIBLE
        }
        submitBtn.setOnClickListener{
            editBtn.visibility = View.VISIBLE
            submitBtn.visibility = View.GONE

            var newBio = editBioET.text.toString()

            // Send new bio to DB
            userDB.put("bio", newBio)
            userDB.saveInBackground {
                if (it != null){
                    it.localizedMessage?.let { message -> Log.e("User Bio Save:", message) }
                }else{
                    Log.d("User Bio Save:","User bio saved.")
                }
            }

            Toast.makeText(context, newBio, Toast.LENGTH_SHORT).show()
            bioTV.text = newBio

            bioTV.visibility = View.VISIBLE

            editBioET.visibility = View.GONE

        }


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