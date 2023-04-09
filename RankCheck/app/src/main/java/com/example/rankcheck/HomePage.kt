package com.example.rankcheck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val switch = findViewById<Button>(R.id.gameSearchBtn)
        val goal = Intent(this, GamesRV::class.java)
        switch.setOnClickListener {
            Toast.makeText(it.context,"Clicked Button",Toast.LENGTH_SHORT).show()
            startActivity(goal)
        }
    }
}