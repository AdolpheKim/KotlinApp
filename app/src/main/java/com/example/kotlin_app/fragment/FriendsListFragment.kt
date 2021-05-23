package com.example.kotlin_app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.friend_list.FriendsListAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FriendsListFragment : Fragment() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private lateinit var textView: TextView
    private var friends: MutableList<String>? = mutableListOf()

    private val store = Firebase.firestore
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_friends_list, container, false)
        InitRecycler(v)
        return v
    }

    fun newInstant() : FriendsListFragment {
        val args = Bundle()
        val fragment = FriendsListFragment()
        fragment.arguments = args

        return fragment
    }

    fun InitRecycler(v : View){

        val userData = arrayListOf<ArrayList<Any?>>()
        val currentUser = auth.currentUser?.email

        recyclerViewFriendsList = v.findViewById(R.id.recyclerView3)
        textView = v.findViewById(R.id.textViewName)

        friends?.add("")
        store.collection("UserData")
            .document(currentUser!!)
            .collection("friends")
            .get()
            .addOnSuccessListener { result ->
                friends?.clear()
                for (i in result) {
                    friends?.add(i["name"].toString())
                }
                Log.d("","$result")
                store.collection("UserData")
                    .get()
                    .addOnSuccessListener { result ->
                        for (i in result) {
                            val Data = arrayListOf<Any?>()
                            Data.add(i["name"])
                            Data.add(i["image"])
                            if(currentUser.equals(i["email"])) textView.text = i["name"].toString()
                            else if( i["name"] in friends!! ) userData.add(Data)
                        }
                        recyclerViewFriendsList.adapter = FriendsListAdapter(userData)
                    }
            }

    }
}