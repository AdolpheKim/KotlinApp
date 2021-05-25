package com.example.kotlin_app.friend_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.ChatActivity
import com.example.kotlin_app.R

class ChattingListAdapter(private val dataset: ArrayList<ArrayList<Any?>>) :
    RecyclerView.Adapter<FriendsListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends_list, parent, false)

        return FriendsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsListViewHolder, position: Int) {
        holder.textView.text = dataset[position][0].toString()
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.itemView.setOnClickListener{
            val newIntent = Intent(holder.itemView?.context, ChatActivity::class.java)
            ContextCompat.startActivity(holder.itemView?.context, newIntent, null)
        }
    }

    override fun getItemCount() = dataset.size
}