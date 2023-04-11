package com.example.rankcheck

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

object Login {
    fun checkLogin(usernameView: EditText, passwordView: EditText): Boolean{
        val query = ParseQuery.getQuery<ParseObject>("Users")
        val username = usernameView.text.toString()
        val password = Util.hashPassword(passwordView.text.toString()).toString()

        query.whereContains("username", username).whereContains("password", password)
        val loggedUser = query.find()

        if(loggedUser.isNullOrEmpty()){
            return false
        }
        return true
    }

}