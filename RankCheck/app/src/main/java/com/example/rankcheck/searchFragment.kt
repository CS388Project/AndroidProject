package com.example.rankcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import com.parse.ParseQuery


class searchFragment: Fragment()  {
    lateinit var usersrv: RecyclerView
    lateinit var userslist: MutableList<DisplayUser>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        usersrv = itemView.findViewById(R.id.userslist)
        val text = itemView.findViewById<EditText>(R.id.searchbar)
        userslist = arrayListOf()
        val adapter = activity?.let { UsersAdapter(it, userslist) }
        usersrv.adapter = adapter
        usersrv.layoutManager = LinearLayoutManager(activity)

        text.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                userslist = getUsers(text)
                val adapter = activity?.let { UsersAdapter(it, userslist) }
                usersrv.adapter = adapter
                usersrv.layoutManager = LinearLayoutManager(activity)

                handled = true
            }
            handled
        })
    }

}

fun getUsers(username: EditText): MutableList<DisplayUser> {
    val query = ParseQuery.getQuery<ParseObject>("Users")
    val username = username.text.toString()
    val list : MutableList<DisplayUser> = arrayListOf()
    query.whereContains("username", username)
    var users = query.find()

    if (!users.isNullOrEmpty()) {
        for( user in users.iterator())
        {
            val user = DisplayUser(user.getString("username"))
            if(user.username == MainActivity.SESSION_USER )
            {
                continue
            }
            list.add(user)
        }
    }

    return list
}