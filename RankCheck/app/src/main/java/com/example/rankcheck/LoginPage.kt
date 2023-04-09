package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val intentMain = Intent(this, MainActivity::class.java)

        val newUser = findViewById<TextView>(R.id.txtNewUser)
        val loginBtn = findViewById<Button>(R.id.login_button)
        val usernameView = findViewById<EditText>(R.id.login_username)
        val passwordView = findViewById<EditText>(R.id.login_password)

        loginBtn.setOnClickListener {
            val loggedin = Login.checkLogin(usernameView, passwordView)
            if(loggedin){
                startActivity(intentMain)
                finish()
            }
            else{
                Toast.makeText(it.context, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
            }
        }

        newUser.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()
        }

    }
}