package com.example.rankcheck

class GameFetcher{
    companion object{
        val gameTitles = listOf("League of Legends", "Valorant", "CS:GO")
        val gameDescription = listOf(
            "League of Legends, commonly referred to as League, is a 2009 " +
                "multiplayer online battle arena video game developed and published by Riot Games. " +
                "Inspired by Defense of the Ancients, a custom map for Warcraft III, Riot's founders " +
                "sought to develop a stand-alone game in the same genre.",
            "Placeholder Description",
            "Placeholder Description")
        val gameLogo = listOf(
            R.drawable.league_cover_image,
            R.drawable.rectangle_border_black_900_radius_4,
            R.drawable.rectangle_border_black_900_radius_4)
        fun getGames(): MutableList<DisplayGame> {
            var games : MutableList<DisplayGame> = ArrayList()
            for (i in 0..2) {
                val game = DisplayGame(gameTitles[i], gameDescription[i], gameLogo[i])
                games.add(game)
            }
            return games
        }
    }
}