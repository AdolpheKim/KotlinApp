package com.example.kotlin_app.friend_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.add_friend.AddFriendActivity
import com.example.kotlin_app.MainActivity
import com.example.kotlin_app.R
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
                    Log.d("",i.toString())
                }
                recyclerViewFriendsList = findViewById(R.id.recyclerView3)
                recyclerViewFriendsList.adapter = FriendsListAdapter(userData)
            }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_friend_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val newIntent = Intent(this, AddFriendActivity::class.java)
        startActivity(newIntent)

        return super.onOptionsItemSelected(item)
    }

    private fun startMainActivity(){
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent)
    }
}

