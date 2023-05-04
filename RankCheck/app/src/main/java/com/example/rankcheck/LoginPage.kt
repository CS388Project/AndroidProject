package com.example.rankcheck

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
        val loginUsername = readSharedPreferences()
        if(loginUsername?.isEmpty() == false){
            intentMain.putExtra("SESSION_USER", loginUsername)
            startActivity(intentMain)
            finish()
        }
        val newUser = findViewById<TextView>(R.id.txtNewUser)
        val loginBtn = findViewById<Button>(R.id.login_button)
        val usernameView = findViewById<EditText>(R.id.login_username)
        val passwordView = findViewById<EditText>(R.id.login_password)


        loginBtn.setOnClickListener {
            val loggedin = Login.checkLogin(usernameView, passwordView)
            var username = usernameView.text.toString()
            var password = passwordView.text.toString()
            if(loggedin){
                saveSharedPreferences(username, password)
                intentMain.putExtra("SESSION_USER", username)
                startActivity(intentMain)
                finish()
            }
            else{
                Toast.makeText(it.context, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
            }
            usernameView.setText("") // This will wipe, make sure to store data before wiping
            passwordView.setText("")
        }

        newUser.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun readSharedPreferences(): String?{
        var sharedpreferences: SharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        var username: String? = sharedpreferences.getString("Username", "")
        return username
    }
    fun saveSharedPreferences(username: String, password: String) {
        var sharedpreferences: SharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        val editor = sharedpreferences.edit()

        editor.putString("Username",username)
        editor.putString("Password",password)
        editor.apply()
        editor.commit()
    }
}