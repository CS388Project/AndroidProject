package com.example.rankcheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rankcheck.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val homeFragment: Fragment = homeFragment()
        val nav: BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener { item->
            lateinit var fragment: Fragment
            R.id.games
            when (item.itemId) {
                R.id.home -> fragment = homeFragment()
                R.id.games -> fragment = gamesFragment()
                R.id.search -> fragment = searchFragment()
                R.id.profile -> fragment = profileFragment()
            }
            replaceFragment(fragment)
            true
        }

        nav.selectedItemId = R.id.home
    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.MainFrame, articleListFragment)
        fragmentTransaction.commit()
    }

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