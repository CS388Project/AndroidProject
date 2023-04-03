package com.example.rankcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.parse.ParseObject

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        val intentMain = Intent(this, MainActivity::class.java)

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
                val newUserObject = ParseObject("Users")
                newUserObject.put("username", regUser.text.toString())
                newUserObject.put("password", regPass.text.toString())
                newUserObject.saveInBackground {
                    if (it != null){
                        it.localizedMessage?.let { message -> Log.e("Registration", message) }
                    }else{
                        Log.d("Registration","Object saved.")
                    }
                }
                regUser.setText("")
                regPass.setText("")
                regPassConfirm.setText("")
                Toast.makeText(this, "Registered New User!", Toast.LENGTH_LONG).show()
                //startActivity(intentMain)
            }
        }

        retUser.setOnClickListener{
            startActivity(intentMain)
        }
    }
}