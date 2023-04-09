package com.example.rankcheck

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val games: List<GamesList>) : RecyclerView.Adapter<GameAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameTitleTextView: TextView
        val gameDescriptionTextView: TextView
        val gameLogoImageView: ImageView
        init {
            gameTitleTextView = itemView.findViewById(R.id.gameTitle)
            gameDescriptionTextView = itemView.findViewById(R.id.gameDescription)
            gameLogoImageView = itemView.findViewById<ImageView>(R.id.gameImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.games_list, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games.get(position)
        holder.gameTitleTextView.text = game.gameTitle
        holder.gameDescriptionTextView.text = game.gameDescription
        holder.gameLogoImageView.contentDescription = game.gameImage //FIX THIS

    }

}