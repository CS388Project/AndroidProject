package com.example.rankcheck

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import androidx.appcompat.app.AppCompatActivity

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {
    private lateinit var gameLogoImageView: ImageView
    private lateinit var gameTitleTextView: TextView
    private lateinit var gameDescriptionTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)

        // TODO: Find the views for the screen
        gameLogoImageView = findViewById(R.id.gameImage2)
        gameTitleTextView = findViewById(R.id.gameTitle)
        gameDescriptionTextView = findViewById(R.id.gameDescription)
        // TODO: Get the extra from the Intent
        val article = intent.getSerializableExtra(GAME_EXTRA) as DisplayGame
        // TODO: Set the title, byline, and abstract information from the article
        gameTitleTextView.text = article.headline
        gameDescriptionTextView.text = article.abstract
        // TODO: Load the media image
        Glide.with(this)
            .load(article.mediaImageUrl)
            .into(gameLogoImageView)
    }

}