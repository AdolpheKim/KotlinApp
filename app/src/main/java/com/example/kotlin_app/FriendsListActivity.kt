package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FriendsListActivity : AppCompatActivity() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private lateinit var data : Array<String>

    private val auth = Firebase.auth
    private val store : FirebaseFirestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)

        val currentUser : FirebaseUser? = auth.currentUser

        if(currentUser == null){
            startMainActivity()
        }

        recyclerViewFriendsList = findViewById(R.id.recyclerView3)
        recyclerViewFriendsList.adapter = FriendsListAdapter(data)
    }

    private fun startMainActivity(){
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent)
    }
}