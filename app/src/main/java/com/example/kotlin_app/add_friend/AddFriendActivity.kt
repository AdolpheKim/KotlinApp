package com.example.kotlin_app.add_friend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.friend_list.AddFriendAdapter
import com.example.kotlin_app.friend_list.FriendsListAdapter
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFriendActivity : AppCompatActivity() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private lateinit var toolbar : Toolbar;

    private val auth = Firebase.auth
    private val store : FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        val currentUser : FirebaseUser? = auth.currentUser
        val userData = arrayListOf<ArrayList<Any?>>()


        store.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (i in result) {
                    val Data = arrayListOf<Any?>()
                    Data.add(i["name"])
                    Data.add(i["image"])
                    userData.add(Data)
                }
                Log.d("에바",result.toString())
                recyclerViewFriendsList = findViewById(R.id.recyclerView2)
                recyclerViewFriendsList.adapter = AddFriendAdapter(userData)
            }
    }
}