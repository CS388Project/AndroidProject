package com.example.rankcheck

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GamesRV : AppCompatActivity() {
    lateinit var games: MutableList<DisplayGame>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_recycler_view)

        val gamesRV = findViewById<RecyclerView>(R.id.gamesListRv)
        games = GameFetcher.getGames()


        val adapter = GameAdapter(this, games)

        gamesRV.adapter = adapter

        gamesRV.layoutManager = LinearLayoutManager(this)
    }
}