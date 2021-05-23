package com.example.kotlin_app.add_friend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.MainActivity
import com.example.kotlin_app.R
import com.example.kotlin_app.friend_list.AddFriendAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFriendActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private lateinit var editText : EditText
    private lateinit var imageViewFind : ImageView
    private lateinit var imageViewBack : ImageView
    private lateinit var currentUser : String
    private var userData = arrayListOf<ArrayList<Any?>>()

    private val auth = Firebase.auth
    private val store : FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        currentUser = auth.currentUser?.email.toString()

        editText = findViewById(R.id.editTextFind)
        imageViewBack = findViewById(R.id.imageViewBack)
        imageViewFind = findViewById(R.id.imageViewFind)
        recyclerViewFriendsList = findViewById(R.id.recyclerView2)

        imageViewFind.setOnClickListener(this)
        imageViewBack.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imageViewFind) {

            store.collection("UserData")
                .document(editText.text.toString())
                .get()
                .addOnSuccessListener { result ->
                    if(!userData.isNullOrEmpty()) userData = arrayListOf()
                    val Data = arrayListOf<Any?>()
                    Data.add(result["name"])
                    Data.add(result["image"])
                    if(currentUser != result["email"]) userData.add(Data)
                    Log.d("에바", result.toString())

                    recyclerViewFriendsList.adapter = AddFriendAdapter(userData)
                }
        }
        else if(v?.id == R.id.imageViewBack){
            val newIntent = Intent(this, MainActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent)
        }
    }
}