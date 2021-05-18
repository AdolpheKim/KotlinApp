package com.example.kotlin_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class FriendsListViewHolder(v : View) : RecyclerView.ViewHolder(v){

    val textView: TextView
    val circleImageView : CircleImageView

    init {
        textView = v.findViewById(R.id.textViewName)
        circleImageView = v.findViewById(R.id.ImageViewProfile)
    }

}