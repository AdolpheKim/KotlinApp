package com.example.kotlin_app.friend_list

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        Log.d("???","$dataset")
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.ImageView.setOnClickListener{
            store.collection("UserData")
                .document(currentUser)
                .collection("friends")
                .document(dataset[position][2].toString())
                .set(hashMapOf(
                    "name" to dataset[position][0],
                    "image" to dataset[position][1],
                    "email" to dataset[position][2]
                ))
                .addOnSuccessListener {
                    val newIntent = Intent(holder.itemView?.context, MainActivity::class.java)
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(holder.itemView?.context, newIntent, null)
                    Log.d("크윽...","해치웠나...?")
                }
        }
    }

    override fun getItemCount() = dataset.size
}