package com.example.kotlin_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class FriendsListViewHolder(v : View) : RecyclerView.ViewHolder(v){

    val textView: TextView = v.findViewById(R.id.textView)
    val circleImageView : CircleImageView = v.findViewById(R.id.circleImageView)

}