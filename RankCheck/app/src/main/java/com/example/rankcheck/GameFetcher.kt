package com.example.rankcheck

class GameFetcher{
    companion object{
        val gameTitles = listOf("League of Legends", "Apex Legends", "Rocket League")
        val gameDescription = listOf(
            "League of Legends, commonly referred to as League, is a 2009 " +
                "multiplayer online battle arena video game developed and published by Riot Games. " +
                "Inspired by Defense of the Ancients, a custom map for Warcraft III, Riot's founders " +
                "sought to develop a stand-alone game in the same genre.",
            "Apex Legends is the award-winning, free-to-play Hero Shooter from Respawn Entertainment. " +
                    "Master an ever-growing roster of legendary characters with powerful abilities, and " +
                    "experience strategic squad play and innovative gameplay in the next evolution of Hero " +
                    "Shooter and Battle Royale.",
            "Rocket League is a high-powered hybrid of arcade-style soccer and vehicular mayhem with easy-to-understand " +
                    "controls and fluid, physics-driven competition. Hit the field by yourself or with friends in 1v1, 2v2, " +
                    "and 3v3 Online Modes, or enjoy Extra Modes like Rumble, Snow Day, or Hoops. Unlock items in Rocket Pass, " +
                    "climb the Competitive Ranks, compete in Competitive Tournaments, complete Challenges, enjoy cross-platform " +
                    "progression and more! The field is waiting. Take your shot!")
        val gameLogo = listOf(
            R.drawable.league_cover_image,
            R.drawable.apex_legends_image,
            R.drawable.rocket_league_image)
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