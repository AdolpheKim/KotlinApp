package com.example.kotlin_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.friend_list.FriendsListAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChattingListFragment : Fragment() {

    private lateinit var recyclerViewFriendsList : RecyclerView
    private val store = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userData = arrayListOf<ArrayList<Any?>>()
        val v = inflater.inflate(R.layout.fragment_chatting_list, container, false)

        recyclerViewFriendsList = v.findViewById(R.id.recyclerView3)
        store.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (i in result) {
                    val Data = arrayListOf<Any?>()
                    Data.add(i["name"])
                    Data.add(i["image"])
                    userData.add(Data)
                }

                recyclerViewFriendsList.adapter = FriendsListAdapter(userData)
            }
        return v
    }

    public fun newInstant() : ChattingListFragment {
        val args = Bundle()
        val fragment = ChattingListFragment()
        fragment.arguments = args

        return fragment
    }

}