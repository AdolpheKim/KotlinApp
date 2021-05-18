package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FriendsListActivity : AppCompatActivity() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private lateinit var toolbar : Toolbar;

    private val auth = Firebase.auth
    private val store : FirebaseFirestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.useraddicon)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val userData = arrayListOf<ArrayList<Any?>>()

        val currentUser : FirebaseUser? = auth.currentUser

        if(currentUser == null){
            startMainActivity()
        }

        store.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (i in result) {
                    val Data = arrayListOf<Any?>()
                    Data.add(i["name"])
                    Data.add(i["image"])
                    userData.add(Data)
                }
                recyclerViewFriendsList = findViewById(R.id.recyclerView3)
                recyclerViewFriendsList.adapter = FriendsListAdapter(userData)
            }
    }

    private fun startMainActivity(){
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent)
    }
}

