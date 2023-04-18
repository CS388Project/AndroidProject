package com.example.rankcheck

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val USER_EXTRA = "USER_EXTRA"

class UsersAdapter(private val context: FragmentActivity, private val users: MutableList<DisplayUser>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.users_lists, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        holder.bind(user)

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        private val userName = itemView.findViewById<TextView>(R.id.userName)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(userInfo: DisplayUser) {
            userName.text = userInfo.username
        }

        override fun onClick(p0: View?) {
            val user = users[adapterPosition]

            val intent = Intent(context, LoginPage::class.java)
            intent.putExtra(USER_EXTRA, user)
            context.startActivity(intent)
        }
    }

}