package com.example.kotlin_app.friend_list

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.MainActivity
import com.example.kotlin_app.R
import com.example.kotlin_app.add_friend.AddFriendViewHolder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFriendAdapter(private val dataset: ArrayList<ArrayList<Any?>>) :
    RecyclerView.Adapter<AddFriendViewHolder>(){

    private lateinit var currentUser : String

    private val store = Firebase.firestore
    private val auth = Firebase.auth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_friend, parent, false)
        currentUser = auth.currentUser?.email.toString()
        return AddFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendViewHolder, position: Int) {
        holder.textView.text = dataset[position][0].toString()
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.ImageView.setOnClickListener{
            store.collection("UserData")
                .document(currentUser)
                .collection("friends")
                .document(holder.textView.text.toString())
                .set(hashMapOf(
                    "name" to holder.textView.text.toString()
                ))
                .addOnSuccessListener {
                    Log.d("크윽...","해치웠나...?")
                }
        }
    }

    override fun getItemCount() = dataset.size
}