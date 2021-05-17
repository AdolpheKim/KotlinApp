package com.example.kotlin_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FriendsListAdapter(private val dataset: Array<String>) :
            RecyclerView.Adapter<FriendsListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends_list, parent, false)

        return FriendsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsListViewHolder, position: Int) {
        holder.textView.text = dataset[position]
        holder.circleImageView.setImageResource(R.drawable.profile)
    }

    override fun getItemCount() = dataset.size
}