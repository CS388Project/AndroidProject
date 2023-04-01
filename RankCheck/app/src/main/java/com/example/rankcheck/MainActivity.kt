package com.example.rankcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.ParseObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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