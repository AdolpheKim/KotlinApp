package com.example.kotlin_app.friend_list

import android.app.Activity
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FriendsListAdapter(private val dataset: ArrayList<ArrayList<Any?>>) :
            RecyclerView.Adapter<FriendsListViewHolder>(), View.OnCreateContextMenuListener, View.OnContextClickListener{

    private lateinit var database: DatabaseReference
    private var currentUser : String? = ""
    private var user : String? = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends_list, parent, false)
        view.setOnCreateContextMenuListener(this)
        database = Firebase.database.reference
        currentUser = Firebase.auth.currentUser?.toString()
        return FriendsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsListViewHolder, position: Int) {
        user = dataset[position][0].toString()
        holder.textView.text = dataset[position][0].toString()
        holder.circleImageView.setImageResource(R.drawable.profile)
        holder.itemView.setOnLongClickListener{
            holder.itemView.showContextMenu()
        }
    }

    override fun getItemCount() = dataset.size

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenuInfo?
    ){
        (v?.context as Activity).menuInflater.inflate(R.menu.menu_chatting, menu)
    }

    override fun onContextClick(v: View?): Boolean {
        val roomName = currentUser!! + "," + user
        val roomMember = listOf(currentUser, user)
        database.child(roomName).child("RoomMember").setValue(roomMember)
            .addOnSuccessListener {
                Log.d("","아 ㅋㅋ 성공이 복사가 된다구")
            }
            .addOnFailureListener() {
                Log.d("","아 ㅋㅋ 실패가 복사가 된다구 ㄹㅇㅋㅋ")
            }
        return true
    }
}