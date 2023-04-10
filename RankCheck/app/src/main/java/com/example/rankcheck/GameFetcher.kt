package com.example.rankcheck

class GameFetcher{
    companion object{
        val gameTitles = listOf("CS:GO", "Valorant", "Add Third Game")
        val gameDescription = "Welcome to the Android Kotlin Course! We're excited to have you join us and learn how to develop Android apps using Kotlin. Here are some tips to get started."
        val gameLogo = "@drawable/rectangle_border_black_900_radius_4.xml"
        fun getGames(): MutableList<DisplayGame> {
            var games : MutableList<DisplayGame> = ArrayList()
            for (i in 0..2) {
                val game = DisplayGame(gameTitles[i], gameDescription, gameLogo,)
                games.add(game)
            }
            return games
        }
    }
}