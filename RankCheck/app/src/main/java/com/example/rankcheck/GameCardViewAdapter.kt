package com.example.rankcheck

import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.example.rankcheck.databinding.ProfileGamesListBinding

class GameCardViewAdapter(private val context: Context?, private val games: MutableList<DisplayGame>, val listener: SetOnItemClickListener) : RecyclerView.Adapter<GameCardViewAdapter.ViewHolder>(){
    interface SetOnItemClickListener {
        fun onItemClick()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCardViewAdapter.ViewHolder {
//        val from = LayoutInflater.from(parent.context)
//        val binding = FriendsCardCellBinding.inflate(from, parent, false)

        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        val binding: View = inflater
            .inflate(
                R.layout.profile_games_list,
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gameCardView.setOnClickListener {
//            Toast.makeText(context,"Clicked...",Toast.LENGTH_LONG).show()
            listener.onItemClick()
        }

        Glide.with(context!!)
            .load("@drawable/img_lock.xml")
            .into(holder.gameLogoImageView)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val gameTitleTextView = itemView.findViewById<TextView>(R.id.gameTitle)
        val gameLogoImageView = itemView.findViewById<ImageView>(R.id.gameImage)
        val gameDescriptionTV = itemView.findViewById<TextView>(R.id.gameDescription)
        val gameCardView = itemView.findViewById<CardView>(R.id.cardView)

    }
}