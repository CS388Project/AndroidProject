package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.parse.ParseObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val newUser = findViewById<TextView>(R.id.txtNewUser)
        val loginBtn = findViewById<Button>(R.id.login_button)

        val username = findViewById<EditText>(R.id.login_username)
        username.setText("")
        val password = findViewById<EditText>(R.id.login_password)
        password.setText("")

        loginBtn.setOnClickListener {
            username.setText("") // This will wipe, make sure to store data before wiping
            password.setText("")
            Toast.makeText(it.context, "Clicked Login Button!", Toast.LENGTH_SHORT).show()
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