package com.example.kotlin_app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.friend_list.FriendsListAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FriendsListFragment : Fragment() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private val store = Firebase.firestore
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val userData = arrayListOf<ArrayList<Any?>>()
        val v = inflater.inflate(R.layout.fragment_friends_list, container, false)

        recyclerViewFriendsList = v.findViewById(R.id.recyclerView3)
        store.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (i in result) {
                    val Data = arrayListOf<Any?>()
                    Data.add(i["name"])
                    Data.add(i["image"])
                    userData.add(Data)
                    Log.d("","${auth.currentUser.email}")
                }

                recyclerViewFriendsList.adapter = FriendsListAdapter(userData)
            }
        return v
    }

    public fun newInstant() : FriendsListFragment {
        val args = Bundle()
        val fragment = FriendsListFragment()
        fragment.arguments = args

        return fragment
    }
}