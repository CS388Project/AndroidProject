package com.example.rankcheck

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rankcheck.databinding.FriendsCardCellBinding
import com.example.rankcheck.databinding.ActivityFriendsDetailBinding
import com.parse.ParseObject

const val FRIEND_EXTRA = "FRIEND_EXTRA"
private const val TAG = "FriendListAdapter"
class FriendListAdapter(private val context: FragmentActivity, private val friends: MutableList<FriendsList>) : RecyclerView.Adapter<CardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FriendsCardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.findFriend(friends[position])
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        private val friendNameTextView = itemView.findViewById<TextView>(R.id.friendName)
        private val userPFPImageView = itemView.findViewById<ImageView>(R.id.profilePic)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(friend: FriendsList) {
            friendNameTextView.text = friend.friendUsername

            Glide.with(context)
                .load(friend.pfp)
                .into(userPFPImageView)
        }

        override fun onClick(p0: View?) {
            val friend = friends[adapterPosition]

            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FRIEND_EXTRA, friend)
            context.startActivity(intent)
        }
    }

}