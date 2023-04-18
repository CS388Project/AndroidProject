package com.example.rankcheck

import androidx.recyclerview.widget.RecyclerView
import com.example.rankcheck.databinding.ProfileGamesListBinding

class GameListCardViewHolder(
    private val GameCellBinding: ProfileGamesListBinding
): RecyclerView.ViewHolder(GameCellBinding.root) {
    fun findGames(game: DisplayGame){
        GameCellBinding.gameTitle.text = game.headline
        GameCellBinding.gameDescription.text = game.abstract
        game.mediaImageUrl?.let { GameCellBinding.gameImage.setImageResource(it) }
    }
}