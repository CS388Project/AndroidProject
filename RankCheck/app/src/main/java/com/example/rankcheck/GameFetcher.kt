package com.example.rankcheck

import com.parse.ParseObject
import com.parse.ParseQuery

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
        fun getGames(user : String): MutableList<DisplayGame> {

            val leaguequery = ParseQuery.getQuery<ParseObject>("LeagueUsers")
            val rocketquery = ParseQuery.getQuery<ParseObject>("RocketUsers")
            val apexquery = ParseQuery.getQuery<ParseObject>("ApexUsers")
            var games : MutableList<DisplayGame> = ArrayList()

            leaguequery.whereEqualTo("RC_username", user)
            rocketquery.whereEqualTo("RC_username", user)
            apexquery.whereEqualTo("RC_username", user)
            var league = leaguequery.find()
            var rocket = rocketquery.find()
            var apex = apexquery.find()

            if (!league.isNullOrEmpty())
            {
                val game = DisplayGame(gameTitles[0], gameDescription[0], gameLogo[0])
                games.add(game)
            }
            if (!apex.isNullOrEmpty())
            {
                val game = DisplayGame(gameTitles[1], gameDescription[1], gameLogo[1])
                games.add(game)
            }
            if (!rocket.isNullOrEmpty())
            {
                val game = DisplayGame(gameTitles[2], gameDescription[2], gameLogo[2])
                games.add(game)
            }

            return games
        }
        fun getAllGames(): MutableList<DisplayGame> {
            var games : MutableList<DisplayGame> = ArrayList()
            for (i in 0..2) {
                val game = DisplayGame(gameTitles[i], gameDescription[i], gameLogo[i])
                games.add(game)
            }
            return games
        }
    }
}