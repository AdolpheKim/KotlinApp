package com.example.kotlin_app.chatting

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.ChatActivity
import com.example.kotlin_app.R

class ChattingAdapter (private val dataset: ArrayList<ArrayList<Any?>>) :
    RecyclerView.Adapter<ChattingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends_list, parent, false)

        return ChattingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChattingViewHolder, position: Int) {
        holder.textView.text = dataset[position][0].toString()
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.itemView.setOnClickListener{
            val newIntent = Intent(holder.itemView?.context, ChatActivity::class.java)
            startActivity(holder.itemView?.context, newIntent, null)
        }
    }

    override fun getItemCount() = dataset.size
}