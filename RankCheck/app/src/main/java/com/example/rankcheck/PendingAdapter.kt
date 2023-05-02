package com.example.rankcheck

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import com.parse.ParseQuery
import okhttp3.internal.notify

class PendingAdapter(private val context: FragmentActivity, private val users: MutableList<DisplayUser>) : RecyclerView.Adapter<PendingAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_pending_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        private val userName = itemView.findViewById<TextView>(R.id.userName)
        private val userpic = itemView.findViewById<ImageView>(R.id.Userpic)
        private val confirm = itemView.findViewById<Button>(R.id.confrimbtn)
        private val decline = itemView.findViewById<Button>(R.id.declinebtn)
        init {
            itemView.setOnClickListener(this)
        }
        @SuppressLint("NotifyDataSetChanged")
        fun bind(userInfo: DisplayUser) {
            userName.text = userInfo.username
            if(userInfo.userpic != "null") {
                userpic.setImageBitmap(ImageObj.decodeImage(userInfo.userpic))
            }
            else
            {
                Glide.with(itemView.context)
                    .load(R.drawable.img_user)
                    .into(userpic)
            }
/*
            val newfriend = ParseQuery.getQuery<ParseObject>("Friends")
            newfriend.whereEqualTo("friendUsername", MainActivity.SESSION_USER)
            newfriend.whereEqualTo("username",userInfo.username.toString())
            var find = newfriend.find()
            confirm.setOnClickListener {
                find.first().put("status", "friends")
                find.first().saveInBackground {
                    if (it != null){
                        it.localizedMessage?.let { message -> Log.e("AddFriend", message) }
                    }else{
                        Log.d("AddFriend","failed")
                    }
                    notifyDataSetChanged()
                }
            }
            decline.setOnClickListener {
                find.first().deleteInBackground {
                    if (it != null){
                        it.localizedMessage?.let { message -> Log.e("RemoveFriend", message) }
                    }else{
                        Log.d("RemoveFriend","Friend Removed.")
                    }
                }
                notifyDataSetChanged()
            }*/
        }


        override fun onClick(p0: View?) {
            val user = users[adapterPosition].username

            val intent = Intent(context, UserDetail::class.java)
            intent.putExtra("USER_EXTRA", user)
            context.startActivity(intent)
        }
    }

}