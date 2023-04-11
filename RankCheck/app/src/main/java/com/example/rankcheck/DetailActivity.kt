package com.example.rankcheck

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.parse.ParseObject
import okhttp3.Headers
import org.json.JSONObject

private const val TAG = "DetailActivity"
private const val RAPID_KEY = BuildConfig.RAPID_KEY
private const val LEAGUE_HOST = BuildConfig.LEAGUE_HOST
class DetailActivity : AppCompatActivity() {
    private lateinit var gameLogoImageView: ImageView
    private lateinit var gameTitleTextView: TextView
    private lateinit var gameDescriptionTextView: TextView
    private lateinit var usernameTextView : TextView
    private lateinit var linkButton: Button
    private lateinit var consentCheck: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)

        gameLogoImageView = findViewById(R.id.gameImage2)
        gameTitleTextView = findViewById(R.id.gameTitle)
        gameDescriptionTextView = findViewById(R.id.gameDescription)
        usernameTextView = findViewById(R.id.link_username)
        linkButton = findViewById(R.id.link_button)
        consentCheck = findViewById(R.id.consent_check)

        val game = intent.getSerializableExtra(GAME_EXTRA) as DisplayGame

        gameTitleTextView.text = game.headline
        gameDescriptionTextView.text = game.abstract
        Glide.with(this)
            .load(game.mediaImageUrl)
            .into(gameLogoImageView)

        linkButton.setOnClickListener{
            if(consentCheck.isChecked){
                if(game.headline == "League of Legends")
                    leagueAPIRequest()
            }
            else
                Toast.makeText(this, "Cannot Link Without Consent", Toast.LENGTH_LONG).show()
        }
    }

    fun leagueAPIRequest(){
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["name"] = usernameTextView.text.toString()
        params["region"] = "na"
        val reqHeaders = RequestHeaders()
        reqHeaders["X-RapidAPI-Key"] = RAPID_KEY
        reqHeaders["X-RapidAPI-Host"] = LEAGUE_HOST

        client["https://league-of-legends-galore.p.rapidapi.com/api/getPlayerRank",
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
                                it.localizedMessage?.let { message -> Log.e("Game Link", message) }
                            }else{
                                Log.d("Game Link","User Linked.")
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