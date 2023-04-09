package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val newUser = findViewById<TextView>(R.id.txtNewUser)
        val loginBtn = findViewById<Button>(R.id.login_button)
        val usernameView = findViewById<EditText>(R.id.login_username)
        val passwordView = findViewById<EditText>(R.id.login_password)
        val goal = Intent(this, HomePage::class.java)
        loginBtn.setOnClickListener {
            val loggedin = Login.checkLogin(usernameView, passwordView)
            if(loggedin){
//                setContentView(R.layout.activity_homepage)
                startActivity(goal)

            }
            else{
                Toast.makeText(it.context, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
            }
        }

        newUser.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
        //DB Connection Test
        /*
        val firstObject = ParseObject("FirstClass")
        firstObject.put("message","another one")
        firstObject.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            }else{
                Log.d("MainActivity","Object saved.")
            }
        }*/

    }

}