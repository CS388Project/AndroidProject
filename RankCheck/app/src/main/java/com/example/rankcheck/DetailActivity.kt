package com.example.rankcheck

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.OkHttpClient
import okhttp3.Request

import com.example.rankcheck.MainActivity.Companion.SESSION_USER
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseQuery
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val TAG = "DetailActivity"
private const val RAPID_KEY = BuildConfig.RAPID_KEY
private const val TRACKERGG_KEY = BuildConfig.TRACKERGG_KEY
private const val LEAGUE_HOST = "league-of-legends-galore.p.rapidapi.com"
private const val ROCKET_HOST = "rocket-league1.p.rapidapi.com"
class DetailActivity : AppCompatActivity() {
    private lateinit var gameLogoImageView: ImageView
    private lateinit var gameTitleTextView: TextView
    private lateinit var gameDescriptionTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var linkButton: Button
    private lateinit var removeButton: Button
    private lateinit var consentCheck: CheckBox
    private lateinit var game: DisplayGame
    private lateinit var tableName: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)

        gameLogoImageView = findViewById(R.id.gameImage2)
        gameTitleTextView = findViewById(R.id.gameTitle)
        gameDescriptionTextView = findViewById(R.id.gameDescription)
        usernameTextView = findViewById(R.id.link_username)
        linkButton = findViewById(R.id.link_button)
        removeButton = findViewById(R.id.remove_button)
        consentCheck = findViewById(R.id.consent_check)

        game = intent.getSerializableExtra(GAME_EXTRA) as DisplayGame

        gameTitleTextView.text = game.headline
        gameDescriptionTextView.text = game.abstract
        Glide.with(this)
            .load(game.mediaImageUrl)
            .into(gameLogoImageView)

        // Set table name from Back4App for checking and removing game links
        if(game.headline == "League of Legends")
            tableName = "LeagueUsers"
        else if(game.headline == "Apex Legends")
            tableName = "ApexUsers"
        else if(game.headline == "Rocket League")
            tableName = "RocketUsers"

        // Check to see if game is already linked
        checkGameLink()

        // If game is linked, remove button is visible
        removeButton.setOnClickListener{
            removeGame()
        }

        // If game is not linked, let user link game
        linkButton.setOnClickListener{
            if(consentCheck.isChecked){
                if(game.headline == "League of Legends")
                    leagueAPIRequest()
                if(game.headline == "Apex Legends")
                    apexAPIRequest()
                if(game.headline == "Rocket League")
                    rocketAPIRequest()
            }
            else
                Toast.makeText(this, "Cannot Link Without Consent", Toast.LENGTH_LONG).show()
        }
    }
    fun checkGameLink(){
        val query = ParseQuery.getQuery<ParseObject>(tableName)
        query.whereContains("RC_username", SESSION_USER)
        val userFound = query.find()
        if (!userFound.isNullOrEmpty()) {
            removeLayoutSetup()
        }
    }
    fun removeLayoutSetup(){
        usernameTextView.visibility = View.GONE
        linkButton.visibility = View.GONE
        consentCheck.visibility = View.GONE
        removeButton.visibility = View.VISIBLE
    }

    fun removeGame(){
        val query = ParseQuery.getQuery<ParseObject>(tableName)
        query.whereEqualTo("RC_username", SESSION_USER)
        val userFound = query.find()
        if (!userFound.isNullOrEmpty()) {
            userFound[0].deleteInBackground(){ e ->
                if (e == null) {
                    Log.i("Game Link","Link Deleted")
                    Toast.makeText(applicationContext, "Game Link Successfully Deleted!", Toast.LENGTH_LONG).show()
                } else {
                    Log.e("ERROR: ",e.message!!)
                }
            }
        }
        finish()
    }

    fun leagueAPIRequest(){
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["name"] = usernameTextView.text.toString()
        params["region"] = "na"
        val reqHeaders = RequestHeaders()
        reqHeaders["X-RapidAPI-Key"] = RAPID_KEY
        reqHeaders["X-RapidAPI-Host"] = LEAGUE_HOST

        val url = "https://league-of-legends-galore.p.rapidapi.com/api/getPlayerRank"

        client[ url,
                reqHeaders,
                params,
                object : JsonHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        response: JsonHttpResponseHandler.JSON
                    ) {
                        val results : JSONObject = response.jsonArray.get(0) as JSONObject
                        val rank = results.getString("rank")
                        Toast.makeText(applicationContext, "Game is linked to your account! Your rank is: $rank", Toast.LENGTH_LONG).show()
                        //Log.i("Test Response: ", rank)

                        val newLeagueObject = ParseObject("LeagueUsers")
                        newLeagueObject.put("RC_username", SESSION_USER)
                        newLeagueObject.put("league_username", usernameTextView.text.toString())
                        newLeagueObject.put("rank", rank)
                        newLeagueObject.saveInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("League Link", message) }
                            }else{
                                Log.d("League Link","User Linked.")
                            }
                        }
                        finish()
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        Log.e("ERROR: ", "Failed API Request")
                    }
                }
        ]
    }

    fun apexAPIRequest(){
        val client = AsyncHttpClient()
        val params = RequestParams()
        val reqHeaders = RequestHeaders()
        reqHeaders["TRN-Api-Key"] = TRACKERGG_KEY

        val platform = "origin"
        val userID = usernameTextView.text.toString()
        val url = "https://public-api.tracker.gg/v2/apex/standard/profile/$platform/$userID"
        Log.i("Url: ", url)

        client[ url,
                reqHeaders,
                params,
                object : JsonHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        response: JsonHttpResponseHandler.JSON
                    ) {
                        Log.i("Test Response: ", response.toString())
                        val data = response.jsonObject.getJSONObject("data")
                        val segments = data.getJSONArray("segments").getJSONObject(0)
                        val stats = segments.getJSONObject("stats")
                        val rank = stats.getJSONObject("rankScore").getJSONObject("metadata").getString("rankName")
                        Log.i("Rank: ", rank)

                        Toast.makeText(applicationContext, "Game is linked to your account! Your rank is: $rank", Toast.LENGTH_LONG).show()

                        val newApexObject = ParseObject("ApexUsers")
                        newApexObject.put("RC_username", SESSION_USER)
                        newApexObject.put("apex_username", usernameTextView.text.toString())
                        newApexObject.put("rank", rank)
                        newApexObject.saveInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("Apex Link", message) }
                            }else{
                                Log.d("Apex Link","User Linked.")
                            }
                        }
                        finish()
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        Log.e("ERROR: ", "Failed API Request")
                    }
                }
        ]
    }

    fun rocketAPIRequest(){
        val client = AsyncHttpClient()
        val params = RequestParams()
        val reqHeaders = RequestHeaders()
        reqHeaders["User-Agent"] = "RapidAPI Playground"
        reqHeaders["Accept-Encoding"] = "identity"
        reqHeaders["X-RapidAPI-Key"] = RAPID_KEY
        reqHeaders["X-RapidAPI-Host"] = ROCKET_HOST

        val userID = usernameTextView.text.toString()
        val url = "https://rocket-league1.p.rapidapi.com/ranks/$userID"
        Log.i("Url: ", url)

        client[ url,
                reqHeaders,
                params,
                object : JsonHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        response: JsonHttpResponseHandler.JSON
                    ) {
                        Log.i("Test Response: ", response.toString())
                        val rankArray : JSONArray = response.jsonObject.getJSONArray("ranks")
                        val standardRanked : JSONObject = rankArray.getJSONObject(2)
                        val rank = standardRanked.getString("rank")
                        Log.i("Rank: ", rank)

                        Toast.makeText(applicationContext, "Game is linked to your account! Your rank is: $rank", Toast.LENGTH_LONG).show()

                        val newRocketObject = ParseObject("RocketUsers")
                        newRocketObject.put("RC_username", SESSION_USER)
                        newRocketObject.put("rocket_username", usernameTextView.text.toString())
                        newRocketObject.put("rank", rank)
                        newRocketObject.saveInBackground {
                            if (it != null){
                                it.localizedMessage?.let { message -> Log.e("Rocket League Link", message) }
                            }else{
                                Log.d("Rocket League Link","User Linked.")
                            }
                        }
                        finish()
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        Log.e("ERROR: ", "Failed API Request")
                    }
                }
        ]
    }
}