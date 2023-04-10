package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        val intentMain = Intent(this, MainActivity::class.java)
        val intentLogin = Intent(this, LoginPage::class.java)

        var regUser = findViewById<EditText>(R.id.register_username)
        var regPass = findViewById<EditText>(R.id.register_password)
        var regPassConfirm = findViewById<EditText>(R.id.register_confirm_password)
        val regButton = findViewById<Button>(R.id.register_button)
        val retUser = findViewById<TextView>(R.id.txtRetUser)

        regButton.setOnClickListener{
            //Passwords don't match, reset pass and passConfirm
            if(regPass.text.toString() != regPassConfirm.text.toString()) {
                Toast.makeText(this, "Passwords Don't Match", Toast.LENGTH_SHORT).show()
                regPass.setText("")
                regPassConfirm.setText("")
            }
            //If match, continue with registration
            else{
                val username = regUser.text.toString()
                val password = regPass.text.toString()

                //Query to find existing username
                val query = ParseQuery.getQuery<ParseObject>("Users")
                query.whereContains("username", username)
                val userFound = query.find()

                //If username not taken, continue with registration
                if(userFound.isNullOrEmpty()){
                    val newUserObject = ParseObject("Users")
                    newUserObject.put("username", username)
                    newUserObject.put("password", Util.hashPassword(password).toString())
                    newUserObject.saveInBackground {
                        if (it != null){
                            it.localizedMessage?.let { message -> Log.e("Registration", message) }
                        }else{
                            Log.d("Registration","User saved.")
                        }
                    }
                    regUser.setText("")
                    regPass.setText("")
                    regPassConfirm.setText("")
                    Toast.makeText(this, "Registered New User! Welcome $username!", Toast.LENGTH_LONG).show()
                    startActivity(intentMain)
                    finish()
                }
                //If username taken, reset
                else{
                    regUser.setText("")
                    regPass.setText("")
                    regPassConfirm.setText("")
                    Toast.makeText(this, "Username Taken", Toast.LENGTH_LONG).show()
                }
            }
            //Log.d("Pass Hash:", Util.hashPassword(regPass.text.toString()).toString())
        }

        //User clicks on returning user
        retUser.setOnClickListener{
            startActivity(intentLogin)
            finish()
        }
    }
}