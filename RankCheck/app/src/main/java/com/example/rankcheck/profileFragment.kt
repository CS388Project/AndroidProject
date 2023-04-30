package com.example.rankcheck


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rankcheck.MainActivity.Companion.SESSION_USER
import com.parse.ParseObject
import com.parse.ParseQuery
import java.io.ByteArrayOutputStream


const val USER_EXTRA = "USER_EXTRA"
class profileFragment: Fragment()  {
    lateinit var profileUsername: TextView
    lateinit var profileImage: ImageView
    lateinit var friendsRV: RecyclerView
    private lateinit var friends: MutableList<FriendsList>
    lateinit var gamesRV: RecyclerView
    lateinit var games: MutableList<DisplayGame>
    val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034

    lateinit var userDB: ParseObject


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        profileUsername = itemView.findViewById(R.id.username)
        profileUsername.text = SESSION_USER
        profileImage = itemView.findViewById(R.id.imageUser)


        val editBtn = itemView.findViewById<Button>(R.id.editBtn)
        var submitBtn = itemView.findViewById<Button>(R.id.editSubmit)

        var bioTV = itemView.findViewById<TextView>(R.id.usrBio)
        var editBioET = itemView.findViewById<EditText>(R.id.editBioET)

        // Checks for the username that is set in textview to grab right bio
        val userLookup = profileUsername.text.toString()
        val query = ParseQuery.getQuery<ParseObject>("Users")
        query.whereContains("username", userLookup)
        userDB = query.first
        bioTV.text = userDB.getString("bio")
        profileImage.setImageBitmap(decodeImage(userDB.getString("image").toString()))
        profileImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        }
        editBtn.setOnClickListener {
            editBtn.visibility = View.GONE
            submitBtn.visibility = View.VISIBLE

            bioTV.visibility = View.GONE

            var currentBio = bioTV.text.toString()
            editBioET.setText(currentBio)
            Toast.makeText(context, currentBio, Toast.LENGTH_SHORT).show()

            editBioET.visibility = View.VISIBLE
        }
        submitBtn.setOnClickListener {
            editBtn.visibility = View.VISIBLE
            submitBtn.visibility = View.GONE

            var newBio = editBioET.text.toString()

            // Send new bio to DB
            userDB.put("bio", newBio)
            userDB.saveInBackground {
                if (it != null) {
                    it.localizedMessage?.let { message -> Log.e("User Bio Save:", message) }
                } else {
                    Log.d("User Bio Save:", "User bio saved.")
                }
            }

            Toast.makeText(context, newBio, Toast.LENGTH_SHORT).show()
            bioTV.text = newBio

            bioTV.visibility = View.VISIBLE

            editBioET.visibility = View.GONE

        }



        friendsRV = itemView.findViewById(R.id.friendsListRV)
        friends = FriendFetcher.getFriends(SESSION_USER)

        val adapter = FriendListAdapter(context,friends,object:FriendListAdapter.SetOnItemClickListener{
            override fun onItemClick(position: Int) {

                Toast.makeText(context, "Clicked!",Toast.LENGTH_SHORT).show()
                val intent = Intent(context, UserDetail::class.java)
                intent.putExtra(USER_EXTRA, friends[position].friendUsername)
                context?.startActivity(intent)
            }
        })
//        val adapter = FriendListAdapter(it, friendsList)
        friendsRV.adapter = adapter
        friendsRV.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        gamesRV = itemView.findViewById(R.id.gamesListRV)
        games = GameFetcher.getGames()

        val game_adapter =  GameCardViewAdapter(context, games)
        gamesRV.adapter = game_adapter
        gamesRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        var photo = data!!.extras!!.get("data") as Bitmap
        val matrix = Matrix()
        matrix.postRotate(-90F)

        photo = Bitmap.createBitmap(
            photo,
            0,
            0,
            photo.width,
            photo.height,
            matrix,
            true
        )
        profileImage.setImageBitmap(photo)

        val imageString = encodeImage(photo)
        userDB.put("image", imageString)


//        val scaledBitmap = Bitmap.createScaledBitmap(bitmapOrg, width, height, true)


        Log.e("CUSTOM---->", photo.height.toString())
        Log.e("CUSTOM---->", photo.width.toString())


        userDB.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message -> Log.e("User Image Save:", message) }
            } else {
                Log.d("User Image Save:", "User image saved.")
            }
        }
    }

    fun encodeImage(bitmap: Bitmap): String {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val imagesBytes = bos.toByteArray()
        val base64Image = Base64.encodeToString(imagesBytes, Base64.NO_WRAP)
        return base64Image
    }


    fun decodeImage(image: String): Bitmap {
        val decodedByte: ByteArray = Base64.decode(image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }

}