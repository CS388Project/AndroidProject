package com.example.rankcheck

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val GAME_EXTRA = "GAME_EXTRA"
private const val TAG = "GameAdapter"
class GameAdapter(private val context: FragmentActivity, private val games: MutableList<DisplayGame>) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.games_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games.get(position)
        holder.bind(game)

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        private val gameTitleTextView = itemView.findViewById<TextView>(R.id.gameTitle)
        private val gameDescriptionTextView = itemView.findViewById<TextView>(R.id.gameDescription)
        private val gameLogoImageView = itemView.findViewById<ImageView>(R.id.gameImage)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(game: DisplayGame) {
            gameTitleTextView.text = game.headline
            gameDescriptionTextView.text = game.abstract

            Glide.with(context)
                .load(game.mediaImageUrl)
                .into(gameLogoImageView)
        }

        override fun onClick(p0: View?) {
            val game = games[adapterPosition]

            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(GAME_EXTRA, game)
            context.startActivity(intent)
        }
    }

}