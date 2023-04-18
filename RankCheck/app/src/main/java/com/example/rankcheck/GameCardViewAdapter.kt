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
import com.example.rankcheck.databinding.ProfileGamesListBinding

class GameCardViewAdapter(private val context: FragmentActivity, private val games: MutableList<DisplayGame>) : RecyclerView.Adapter<GameListCardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProfileGamesListBinding.inflate(from, parent, false)
        return GameListCardViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameListCardViewHolder, position: Int) {
        holder.findGames(games[position])
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        private val gameTitleTextView = itemView.findViewById<TextView>(R.id.gameTitle)
        private val gameLogoImageView = itemView.findViewById<ImageView>(R.id.gameImage)
        private val gameDescriptionTV = itemView.findViewById<TextView>(R.id.gameDescription)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(game: DisplayGame) {
            gameTitleTextView.text = game.headline
            gameDescriptionTV.text = game.abstract


            Glide.with(context)
                .load(game.mediaImageUrl)
                .into(gameLogoImageView)
        }

        override fun onClick(p0: View?) {
            val game = games[adapterPosition]
            Log.e("X", "Clicked")
            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(GAME_EXTRA, game)
            context.startActivity(intent)
        }
    }

}