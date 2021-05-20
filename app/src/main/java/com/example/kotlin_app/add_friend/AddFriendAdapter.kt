package com.example.kotlin_app.friend_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.add_friend.AddFriendViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFriendAdapter(private val dataset: ArrayList<ArrayList<Any?>>) :
    RecyclerView.Adapter<AddFriendViewHolder>(){

    private val store = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_friend, parent, false)

        return AddFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendViewHolder, position: Int) {
        holder.textView.text = dataset[position][0].toString()
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.ImageView.setOnClickListener{
            store.collection("UserData")
                .document("email")
                .update("friends","")
        }
    }

    override fun getItemCount() = dataset.size
}