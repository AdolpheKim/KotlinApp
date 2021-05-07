package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FriendsListActivity : AppCompatActivity(), View.OnClickListener {

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)

        val btn = findViewById<Button>(R.id.buttonLogIn)
        btn.setOnClickListener(this)

        val currentUser : FirebaseUser? = auth.currentUser

        if(currentUser == null){
            //startMainActivity()
        }
    }

    override fun onClick(v: View?) {
        startMainActivity()
    }

    private fun startMainActivity(){
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent)
    }
}