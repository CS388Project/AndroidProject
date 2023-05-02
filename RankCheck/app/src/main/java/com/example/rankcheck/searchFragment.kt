package com.example.rankcheck

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
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
    lateinit var pendingrv: RecyclerView
    lateinit var userslist: MutableList<DisplayUser>
    lateinit var pendinguserslist: MutableList<DisplayUser>
    lateinit var search: Button
    lateinit var pending: Button
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
        pendingrv = itemView.findViewById(R.id.pendinglist)
        val confirm = itemView.findViewById<Button>(R.id.confrimbtn)
        val decline = itemView.findViewById<Button>(R.id.declinebtn)
        val text = itemView.findViewById<EditText>(R.id.searchbar)
        userslist = arrayListOf()
        pendinguserslist = arrayListOf()
        val adapter = activity?.let { UsersAdapter(it, userslist) }
        usersrv.adapter = adapter
        usersrv.layoutManager = LinearLayoutManager(activity)

        search = itemView.findViewById(R.id.srchbtn)
        pending = itemView.findViewById(R.id.pndbtn)

        search.setOnClickListener {
            pendingrv.visibility = View.GONE
            usersrv.visibility = View.VISIBLE
            text.visibility = View.VISIBLE

        }

        pending.setOnClickListener {
            pendingrv.visibility = View.VISIBLE
            usersrv.visibility = View.GONE
            text.visibility = View.GONE
        }

        pendinguserslist = getPendingUsers()
        if (pendinguserslist.isNotEmpty())
        {
            val adapter2 = activity?.let { PendingAdapter(it, pendinguserslist) }
            pendingrv.adapter = adapter2
            pendingrv.layoutManager = LinearLayoutManager(activity)

        }

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
            val user = DisplayUser(user.getString("username"), user.getString("image").toString())
            if(user.username == MainActivity.SESSION_USER )
            {
                continue
            }
            list.add(user)
        }
    }

    return list
}

fun getPendingUsers(): MutableList<DisplayUser> {
    val query = ParseQuery.getQuery<ParseObject>("Users")
    val query2 = ParseQuery.getQuery<ParseObject>("Friends")
    val list : MutableList<DisplayUser> = arrayListOf()

    query2.whereEqualTo("friendUsername", MainActivity.SESSION_USER )
    var users = query2.find()

    if (!users.isNullOrEmpty()) {
        for( user in users.iterator())
        {
            if (user.getString("status") == "pending") {
                query.whereEqualTo("username", user.getString("username"))
                var usr = query.find()
                val user =
                    DisplayUser(usr.first().getString("username"), usr.first().getString("image").toString())
                if (user.username == MainActivity.SESSION_USER) {
                    continue
                }
                list.add(user)
            }
        }
    }

    return list
}

