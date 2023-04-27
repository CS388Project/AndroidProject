package com.example.rankcheck

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rankcheck.databinding.FriendsCardCellBinding

const val FRIEND_EXTRA = "USER_EXTRA"
private const val TAG = "FriendListAdapter"
class FriendListAdapter(private val context: Context?, private val friends: MutableList<FriendsList>, val listener: SetOnItemClickListener) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>(){

    interface SetOnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val from = LayoutInflater.from(parent.context)
//        val binding = FriendsCardCellBinding.inflate(from, parent, false)

        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        val binding: View = inflater
            .inflate(
                R.layout.friends_card_cell,
                parent, false
            )


        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.findFriend(friends[position])

        holder.cardView.setOnClickListener {
//            Toast.makeText(context,"Clicked...",Toast.LENGTH_LONG).show()
            listener.onItemClick(position)
        }
        Glide.with(context!!)
                .load("@drawable/img_user.xml")
                .into(holder.userPFPImageView)

        holder.friendNameTextView.text = friends[position].friendUsername

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
         val friendNameTextView = itemView.findViewById<TextView>(R.id.friendName)
         val cardView = itemView.findViewById<CardView>(R.id.cardView)
         val userPFPImageView = itemView.findViewById<ImageView>(R.id.profilePic)

    }

}