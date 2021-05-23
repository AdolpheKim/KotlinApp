package com.example.kotlin_app.chat

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.chatting.ChattingViewHolder

class ChatAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    lateinit var data = mutableListOf<MultiData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View?

    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data[position].type) {
            multi_type1 -> {
                (holder as ChatCurrentViewHolder).bind(data[position])
                holder.setIsRecyclable(false)
            }
            multi_type2 -> {
                (holder as ChatOtherViewHolder).bind(data[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as ChatCurrentViewHolder).bind(data[position])
                holder.setIsRecyclable(false)
            }
        }
    }
}